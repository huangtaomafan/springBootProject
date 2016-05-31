/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 基于注解的session监听器
 * @author liuzq
 * @version $Id: MyHttpSessionListener.java, v 0.1 2016年5月31日 下午4:32:41 liuzq Exp $
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    /** 
     * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        System.err.println("HttpSessionListener Created!");
    }

    /** 
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.err.println("HttpSessionListener Destroyed!");
    }

}
