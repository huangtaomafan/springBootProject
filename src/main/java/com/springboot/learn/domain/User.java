/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User对象模型
 * @author liuzq
 * @version $Id: User.java, v 0.1 2016年4月27日 下午1:52:47 liuzq Exp $
 */
public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -6091272394253747076L;

    /** 主键id */
    private Long              id;

    /** 姓名 */
    private String            name;

    /** 年龄 */
    private Integer           age;

    /** 性别 */
    private String            sex;

    /** 电话(登录用户名) */
    private String            telNo;

    /** 密码 */
    private String            password;

    /** 创建日期 */
    private Date              gmtCreate;

    /** 修改日期 */
    private Date              gmtModify;
    
    /** 空构造器 */
    public User() {
    }

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     * 
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     * 
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>sex</tt>.
     * 
     * @return property value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter method for property <tt>sex</tt>.
     * 
     * @param sex value to be assigned to property sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter method for property <tt>telNo</tt>.
     * 
     * @return property value of telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * Setter method for property <tt>telNo</tt>.
     * 
     * @param telNo value to be assigned to property telNo
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>gmtCreate</tt>.
     * 
     * @return property value of gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property <tt>gmtCreate</tt>.
     * 
     * @param gmtCreate value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * Getter method for property <tt>gmtModify</tt>.
     * 
     * @return property value of gmtModify
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * Setter method for property <tt>gmtModify</tt>.
     * 
     * @param gmtModify value to be assigned to property gmtModify
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

}
