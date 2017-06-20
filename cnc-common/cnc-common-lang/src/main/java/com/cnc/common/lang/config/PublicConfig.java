/**
 * className：PublicConifg.java <br>
 * @version：1.0  <br>
 * date: 2014-11-5-上午10:15:20     <br>
 * Copyright (c)  2014中益智正公司-版权所有   <br>
 */
package com.cnc.common.lang.config;


import com.cnc.common.lang.utils.ResourceUtils;

import java.util.Map;


/**
 * className：PublicConifg <br>
 * Function： 环境配置基础类 <br>
 */
public class PublicConfig {

	/**
	 * 系统文件配置 加载。
	 */
	public static Map<String, String> PUBLIC_SYSTEM = ResourceUtils.getResource("sys-conf").getMap();


	public static final String DUBBO_ADDRESS = PUBLIC_SYSTEM.get("dubbo.registry.address");

}
