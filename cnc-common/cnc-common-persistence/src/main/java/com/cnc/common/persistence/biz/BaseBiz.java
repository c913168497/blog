/**
 * wusc.edu.pay.core.common.biz;
 */
package com.cnc.common.persistence.biz;


import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;

import java.util.List;
import java.util.Map;


/**
 * @描述: 基类Biz接口
 * @作者: haw .
 * @创建时间: 2016-12-15 .
 * @版本: 1.0 .
 */
public interface BaseBiz<T extends BaseEntity> {
	/**
	 * 根据实体对象新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	Long create(T entity);

	/**
	 * 批量保存对象.
	 * 
	 * @param list
	 *            .
	 * @return id .
	 */
	Long create(List<T> list);

	/**
	 * 更新实体对应的记录.
	 * 
	 * @param entity
	 *            .
	 * @return
	 */
	Long update(T entity);

	/**
	 * 批量更新对象.
	 * 
	 * @param list
	 *            .
	 * @return id .
	 */
	Long update(List<T> list);

	/**
	 * 根据ID删除记录.
	 * 
	 * @param id
	 *            .
	 * @return
	 */
	Long deleteById(Long id);
	/**
	 * 根据ID批量删除记录.
	 *
	 * @param ids
	 */
	void deleteByIds(List<Long> ids);

	/**
	 * 根据ID查找记录.
	 * 
	 * @param id
	 *            .
	 * @return entity .
	 */
	T getById(Long id);
	
	/**
	 * 分页查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

	PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	List<T> listBy(Map<String, Object> paramMap);

	List<Object> listBy(Map<String, Object> paramMap, String sqlId);

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	T getBy(Map<String, Object> paramMap);

	Object getBy(Map<String, Object> paramMap, String sqlId);

	/**
	 * 根据序列名称获取下一个值
	 * 
	 * @return
	 */
	String getSeqNextValue(String seqName);
}
