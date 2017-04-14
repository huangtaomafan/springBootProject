/*
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 对象模型父类
 * @author liuzq
 * @version $Id: BaseModel.java, v 0.1 2016年11月24日 下午3:28:15 liuzq Exp $
 */
public class BaseModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -8997635833662724361L;

    /** 
     * 重新默认的toString()，用于输出日志等
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
