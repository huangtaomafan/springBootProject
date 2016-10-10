/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Druid监控的StatFilter
 * @author liuzq
 * @version $Id: DruidStatFilter.java, v 0.1 2016年10月10日 下午6:20:53 liuzq Exp $
 */

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}
