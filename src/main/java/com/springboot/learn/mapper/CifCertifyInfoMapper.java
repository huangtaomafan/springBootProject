/*
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.learn.domain.CifCertifyInfoModel;

/**
 * CIF_CERTIFY_INFO表数据库接口
 * @author liuzq
 * @version $Id: CifCertifyInfoMapper.java, v 0.1 2017年3月8日 下午1:48:05 liuzq Exp $
 */
@Mapper
public interface CifCertifyInfoMapper {

    /**
     * 根据certifyId查询认证信息
     * @param certifyId
     * @return
     */
    CifCertifyInfoModel getCertifyInfoById(String certifyId);

    /**
     * 修改认证信息
     * @param cifCertifyInfoModel
     * @return
     */
    int updateCertifyInfo(CifCertifyInfoModel cifCertifyInfoModel);

    /**
     * 新增认证信息
     * @param cifCertifyInfoModel
     * @return
     */
    int insertCertifyInfo(CifCertifyInfoModel cifCertifyInfoModel);
}
