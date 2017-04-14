/*
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.springboot.learn.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 认证信息
 * @author liuzq
 * @version $Id: CifCertifyInfoModel.java, v 0.1 2017年3月8日 下午3:55:19 liuzq Exp $
 */
public class CifCertifyInfoModel extends BaseModel {

    /** serialVersionUID */
    private static final long serialVersionUID = -5806561711094441805L;

    /** 认证id */
    private String            certifyId;

    /** 真实姓名 */
    private String            realName;

    /** 国籍 */
    private String            national;

    /** 性别  GenderEnum */
    private String            gender;

    /** 职业 */
    private String            profession;

    /** 住址 */
    private String            address;

    /** 联系方式 */
    private String            mobile;

    /** 证件类型  CertTypeEnum */
    private String            certType;

    /** 证件号码 */
    private String            certNo;

    /** 证件号码有效期开始时间 */
    private Date              certValidBeginDate;

    /** 证件号码有效期结束时间 */
    private Date              certValidEndDate;

    /** 创建时间 */
    private Timestamp         gmtCreate;

    /** 修改时间 */
    private Timestamp         gmtModified;

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
     * Getter method for property <tt>realName</tt>.
     * 
     * @return property value of realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Setter method for property <tt>realName</tt>.
     * 
     * @param realName value to be assigned to property realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Getter method for property <tt>national</tt>.
     * 
     * @return property value of national
     */
    public String getNational() {
        return national;
    }

    /**
     * Setter method for property <tt>national</tt>.
     * 
     * @param national value to be assigned to property national
     */
    public void setNational(String national) {
        this.national = national;
    }

    /**
     * Getter method for property <tt>gender</tt>.
     * 
     * @return property value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter method for property <tt>gender</tt>.
     * 
     * @param gender value to be assigned to property gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter method for property <tt>profession</tt>.
     * 
     * @return property value of profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Setter method for property <tt>profession</tt>.
     * 
     * @param profession value to be assigned to property profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * Getter method for property <tt>address</tt>.
     * 
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     * 
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for property <tt>mobile</tt>.
     * 
     * @return property value of mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Setter method for property <tt>mobile</tt>.
     * 
     * @param mobile value to be assigned to property mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Getter method for property <tt>certType</tt>.
     * 
     * @return property value of certType
     */
    public String getCertType() {
        return certType;
    }

    /**
     * Setter method for property <tt>certType</tt>.
     * 
     * @param certType value to be assigned to property certType
     */
    public void setCertType(String certType) {
        this.certType = certType;
    }

    /**
     * Getter method for property <tt>certNo</tt>.
     * 
     * @return property value of certNo
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * Setter method for property <tt>certNo</tt>.
     * 
     * @param certNo value to be assigned to property certNo
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    /**
     * Getter method for property <tt>certValidBeginDate</tt>.
     * 
     * @return property value of certValidBeginDate
     */
    public Date getCertValidBeginDate() {
        return certValidBeginDate;
    }

    /**
     * Setter method for property <tt>certValidBeginDate</tt>.
     * 
     * @param certValidBeginDate value to be assigned to property certValidBeginDate
     */
    public void setCertValidBeginDate(Date certValidBeginDate) {
        this.certValidBeginDate = certValidBeginDate;
    }

    /**
     * Getter method for property <tt>certValidEndDate</tt>.
     * 
     * @return property value of certValidEndDate
     */
    public Date getCertValidEndDate() {
        return certValidEndDate;
    }

    /**
     * Setter method for property <tt>certValidEndDate</tt>.
     * 
     * @param certValidEndDate value to be assigned to property certValidEndDate
     */
    public void setCertValidEndDate(Date certValidEndDate) {
        this.certValidEndDate = certValidEndDate;
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

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

}
