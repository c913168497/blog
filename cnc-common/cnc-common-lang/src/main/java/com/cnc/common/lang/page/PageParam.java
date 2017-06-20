package com.cnc.common.lang.page;

import java.io.Serializable;

/**
 * 
 * @描述: 分页参数传递工具类 .
 * @作者: WuShuicheng .
 * @创建时间: 2013-9-4,下午2:23:47 .
 * @版本: 1.0 .
 */
public class PageParam implements Serializable{
	

	private static final long serialVersionUID = 6297178964005032338L;
	private int pageNum = 1; // 当前页数
	private int numPerPage = 20; // 每页记录数
	private String sort;    //排序字段
	private String order = "asc";   //asc desc 默认asc

	public PageParam() {}

	public PageParam(int pageNum, int numPerPage) {
		super();
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}
	public PageParam(int pageNum, int numPerPage,String sort,String order) {
		super();
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
		this.setSort(sort);//默认会转成下划线风格，如果不需要转，请自行修改！
		this.order = order;
	}


	/** 当前页数 */
	public int getPageNum() {
		return pageNum;
	}

	/** 当前页数 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/** 每页记录数 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/** 每页记录数 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if(sort != null){
			this.sort = toUnderlineName(sort);
		}
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	private  String toUnderlineName(String s) {
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

	@Override
	public String toString() {
		return "PageParam{" +
				"pageNum=" + pageNum +
				", numPerPage=" + numPerPage +
				", sort='" + sort + '\'' +
				", order='" + order + '\'' +
				'}';
	}
}
