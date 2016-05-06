/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.exception;

/**
 * 自定义异常类
 * @author liuzq
 * @version $Id: MyException.java, v 0.1 2016年5月5日 下午5:18:11 liuzq Exp $
 */
public class MyException extends RuntimeException {
    /** serialVersionUID */
    private static final long serialVersionUID = 2682697828587495952L;

    public MyException(String message) {
        super(message);
    }
}
