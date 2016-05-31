/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 基于注解的servletContext监听器
 * @author liuzq
 * @version $Id: MyServletContextListener.java, v 0.1 2016年5月31日 下午4:30:51 liuzq Exp $
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /** 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.err.println("ServletContextListener Destroyed!");
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.err.println("ServletContextListener Initialized!");
        System.err.println(arg0.getServletContext().getServerInfo());
    }

}
