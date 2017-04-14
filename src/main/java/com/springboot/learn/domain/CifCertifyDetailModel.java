/*
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.springboot.learn.domain;

import java.sql.Timestamp;

/**
 * 认证详细信息
 * @author liuzq
 * @version $Id: CifCertifyDetailModel.java, v 0.1 2017年3月9日 下午3:32:54 liuzq Exp $
 */
public class CifCertifyDetailModel extends BaseModel {

    /** serialVersionUID */
    private static final long serialVersionUID = 8955320469558892282L;

    /** 认证id */
    private String            certifyId;

    /** 认证渠道  CertifyChannelEnum */
    private String            certifyChannel;

    /** 认证方式 */
    private String            certifyType;

    /** 创建时间 */
    private Timestamp         gmtCreate;

    /**
     * Getter method for property <tt>certifyId</tt>.
     * 
     * @return property value of certifyId
     */
    public String getCertifyId() {
        return certifyId;
    }

    /**
     * Setter method for property <tt>certifyId</tt>.
     * 
     * @param certifyId value to be assigned to property certifyId
     */
    public void setCertifyId(String certifyId) {
        this.certifyId = certifyId;
    }

    /**
     * Getter method for property <tt>certifyChannel</tt>.
     * 
     * @return property value of certifyChannel
     */
    public String getCertifyChannel() {
        return certifyChannel;
    }

    /**
     * Setter method for property <tt>certifyChannel</tt>.
     * 
     * @param certifyChannel value to be assigned to property certifyChannel
     */
    public void setCertifyChannel(String certifyChannel) {
        this.certifyChannel = certifyChannel;
    }

    /**
     * Getter method for property <tt>certifyType</tt>.
     * 
     * @return property value of certifyType
     */
    public String getCertifyType() {
        return certifyType;
    }

    /**
     * Setter method for property <tt>certifyType</tt>.
     * 
     * @param certifyType value to be assigned to property certifyType
     */
    public void setCertifyType(String certifyType) {
        this.certifyType = certifyType;
    }

    /**
     * Getter method for property <tt>gmtCreate</tt>.
     * 
     * @return property value of gmtCreate
     */
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property <tt>gmtCreate</tt>.
     * 
     * @param gmtCreate value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
