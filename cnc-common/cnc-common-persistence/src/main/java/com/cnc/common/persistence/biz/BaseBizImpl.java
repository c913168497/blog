
package com.cnc.common.persistence.biz;

import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;
import com.cnc.common.persistence.dao.BaseDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @描述: 基类Biz接口实现类
 * @作者: haw .
 * @创建时间: 2016-12-15 .
 * @版本: 1.0 .
 */
@Service("baseBiz")
public abstract class BaseBizImpl<T extends BaseEntity> implements BaseBiz<T> {

	protected abstract BaseDao<T> getDao();

	@Transactional
	public Long create(T entity) {
		return getDao().insert(entity);
	}
	@Transactional
	public Long create(List<T> list) {
		return getDao().insert(list);
	}
	@Transactional
	public Long update(T entity) {
		return getDao().update(entity);
	}
	@Transactional
	public Long update(List<T> list) {
		return getDao().update(list);
	}

	public T getById(Long id) {
		return this.getDao().getById(id);
	}

	/**
	 * 根据ID删除记录.
	 * 
	 * @param id
	 *            .
	 * @return
	 */
	@Transactional
	public Long deleteById(Long id) {
		return this.getDao().deleteById(id);
	}
	/**
	 * 根据ID批量删除记录.
	 *
	 * @param ids
	 */
	@Transactional
	public void deleteByIds(List<Long> ids){
		this.getDao().deleteByIds(ids);
	}

	/**
	 * 分页查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return this.getDao().listPage(pageParam, paramMap);
	}

	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
		return this.getDao().listPage(pageParam, paramMap, sqlId);
	}

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	public List<T> listBy(Map<String, Object> paramMap) {
		return this.getDao().listBy(paramMap);
	}

	public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
		return this.getDao().listBy(paramMap, sqlId);
	}

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	public T getBy(Map<String, Object> paramMap) {
		return this.getDao().getBy(paramMap);
	}

	public Object getBy(Map<String, Object> paramMap, String sqlId) {
		return this.getDao().getBy(paramMap, sqlId);
	}

	/**
	 * 根据序列名称获取下一个值
	 * 
	 * @return
	 */
	public String getSeqNextValue(String seqName) {
		return this.getDao().getSeqNextValue(seqName);
	}

}
