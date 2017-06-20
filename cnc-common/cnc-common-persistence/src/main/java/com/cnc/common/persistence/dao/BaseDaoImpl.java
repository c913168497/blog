package com.cnc.common.persistence.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;
import com.cnc.common.persistence.exception.BizException;
import com.cnc.common.persistence.exception.BizExceptionType;
import com.cnc.common.persistence.mybatis.interceptor.ExecutorInterceptor;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @描述: 数据访问层基础支撑类.
 * @创建时间: 2013-7-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param <T>
 */
public  class BaseDaoImpl<T extends BaseEntity> extends AbstractBaseDao implements BaseDao<T> {

	protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_DELETE_BY_IDS = "deleteByIds";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_GET_BY_CODE = "getByCode";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DruidDataSource druidDataSource;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	public long insert(T t) {

		if (t == null)
			throw new RuntimeException("T is null");

		int result = sessionTemplate.insert(getStatement(SQL_INSERT), t);

		if (result <= 0)
			throw new BizException(BizExceptionType.DB_INSERT_RESULT_0);
		if (t != null && t.getId() != null && result > 0)
			return t.getId();

		return result;
	}

	public long insert(List<T> list) {

		if (list == null || list.size() <= 0)
			return 0;

		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);

		if (result <= 0)
			throw new BizException(BizExceptionType.DB_INSERT_RESULT_0);

		return result;
	}

	public long update(T t) {
		if (t == null)
			throw new RuntimeException("T is null");

		int result = sessionTemplate.update(getStatement(SQL_UPDATE), t);

		if (result <= 0)
			throw new BizException(BizExceptionType.DB_UPDATE_RESULT_0);

		return result;
	}


	public long update(List<T> list) {

		if (list == null || list.size() <= 0)
			return 0;

		int result = 0;

		for (int i = 0; i < list.size(); i++) {
			this.update(list.get(i));
			result += 1;
		}

		if (result <= 0)
			throw new BizException(BizExceptionType.DB_UPDATE_RESULT_0);

		return result;
	}

	public long update(Map<String, Object> paramMap, String sqlId) {

		int result = sessionTemplate.update(getStatement(sqlId), paramMap);
		return result;
	}

	public T getById(long id) {
		return sessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	/*public T getByCode(T t) {
		return sessionTemplate.selectOne(getStatement(SQL_GET_BY_CODE), t);
	}*/

	public long deleteById(long id) {
		return (long) sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	public void deleteByIds(List<Long> ids){
		sessionTemplate.delete(getStatement(SQL_DELETE_BY_IDS),ids);
	}

	@Override
	public void delete(Map<String, Object> paramMap, String sqlId) {
		sessionTemplate.delete(getStatement(sqlId),paramMap);
	}

	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {

		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession().selectList(getStatement(sqlId), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));

		// 统计总记录数
		Object countObject = getSqlSession().selectOne(getStatement(sqlId), new ExecutorInterceptor.CountParameter(paramMap));
		Long count = Long.valueOf(countObject.toString());

		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
	}

	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {


		logger.info("[请求分页参数]:"+pageParam+",[请求查询参数]:"+paramMap);

		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		if(pageParam != null){
			if(pageParam.getOrder() != null && !"".equals(pageParam.getOrder().trim())){
				paramMap.put("order",pageParam.getOrder());
			}

			if(pageParam.getSort() !=  null && !"".equals(pageParam.getSort().trim())){
				paramMap.put("sort",pageParam.getSort());
			}
		}

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = null;
		if(pageParam == null){
			//如果没有携带分页信息，默认查询全部！
			list = getSqlSession().selectList(getStatement(SQL_LIST_PAGE), paramMap);
		}else{
			list = getSqlSession().selectList(getStatement(SQL_LIST_PAGE), paramMap,
					new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));
		}


		// 统计总记录数
		Object countObject = getSqlSession().selectOne(getStatement(SQL_LIST_PAGE),new ExecutorInterceptor.CountParameter(paramMap));
		Long count = Long.valueOf(countObject.toString());

		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list, countResultMap);
		} else {
			if(pageParam == null){
				//显示当前为第一页，当前页数为总记录数
				return new PageBean(1,count.intValue(), count.intValue(), list);
			}else{
				return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> listBy(Map<String, Object> paramMap) {
		return (List) this.listBy(paramMap, SQL_LIST_BY);
	}


	public <E> List<E> listBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		return sessionTemplate.selectList(getStatement(sqlId), paramMap);
	}

	@SuppressWarnings("unchecked")
	public T getBy(Map<String, Object> paramMap) {
		return (T) this.getBy(paramMap, SQL_LIST_BY);
	}


	public Object getBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null || paramMap.isEmpty())
			return null;

		return this.getSqlSession().selectOne(getStatement(sqlId), paramMap);
	}

	public String getStatement(String sqlId) {

		String name = this.getClass().getName();

		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

		return sb.toString();
	}

	/**
	 * 根据序列名称,获取序列值
	 */
	public String getSeqNextValue(String seqName) {
		boolean isClosedConn = false;
		// 获取当前线程的连接
		Connection connection = this.sessionTemplate.getConnection();
		// 获取Mybatis的SQLRunner类
		SqlRunner sqlRunner = null;
		try {
			// 要执行的SQL
			String sql = "";
			// 数据库驱动类
			String driverClass = druidDataSource.getDriver().getClass().getName();
			// 不同的数据库,拼接SQL语句
			if (driverClass.equals("com.ibm.db2.jcc.DB2Driver")) {
				sql = "  VALUES " + seqName.toUpperCase() + ".NEXTVAL";
			}
			if (driverClass.equals("oracle.jdbc.OracleDriver")) {
				sql = "SELECT " + seqName.toUpperCase() + ".NEXTVAL FROM DUAL";
			}
			if (driverClass.equals("com.mysql.jdbc.Driver")) {
				sql = "SELECT  FUN_SEQ('" + seqName.toUpperCase() + "')";
			}
			// 如果状态为关闭,则需要从新打开一个连接
			if (connection.isClosed()) {
				connection = sqlSessionFactory.openSession().getConnection();
				isClosedConn = true;
			}
			sqlRunner = new SqlRunner(connection);
			Object[] args = {};
			// 执行SQL语句
			Map<String, Object> params = sqlRunner.selectOne(sql, args);
			for (Object o : params.values()) {
				return o.toString();
			}
			return null;
		} catch (Exception e) {
			throw new BizException(BizExceptionType.DB_GET_SEQ_NEXT_VALUE_ERROR.getCode(),"获取序列出现错误!序列名称:{%s}", seqName);
		} finally {
			if (isClosedConn) {
				sqlRunner.closeConnection();
			}
		}
	}

	protected   String toUnderlineName(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0) sb.append("_");
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}
}
