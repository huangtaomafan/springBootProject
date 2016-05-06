/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.domain;

import java.io.Serializable;

/**
 * 返回结果基础类
 * @author liuzq
 * @version $Id: SpringBootResult.java, v 0.1 2016年5月4日 下午1:54:00 liuzq Exp $
 */
public class SpringBootResult implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -5850936679046667427L;

    /** 成功标志位*/
    private boolean           success          = false;

    /** 结果码 */
    private String            resultCode;

    /** 结果信息 */
    private String            resultMsg;

    /** 结果数据 */
    private Object            data;

    /**
     * 默认构造函数
     */
    public SpringBootResult() {
    }

    /**
     * Getter method for property <tt>success</tt>.
     * 
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     * 
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     * 
     * @return property value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     * 
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultMsg</tt>.
     * 
     * @return property value of resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * Setter method for property <tt>resultMsg</tt>.
     * 
     * @param resultMsg value to be assigned to property resultMsg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * Getter method for property <tt>data</tt>.
     * 
     * @return property value of data
     */
    public Object getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     * 
     * @param data value to be assigned to property data
     */
    public void setData(Object data) {
        this.data = data;
    }

}
