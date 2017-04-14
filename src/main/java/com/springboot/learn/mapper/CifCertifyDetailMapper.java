/*
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.springboot.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.learn.domain.CifCertifyDetailModel;

/**
 * CIF_CERTIFY_DETAIL表数据库接口
 * @author liuzq
 * @version $Id: CifCertifyDetailMapper.java, v 0.1 2017年3月9日 下午4:16:44 liuzq Exp $
 */
@Mapper
public interface CifCertifyDetailMapper {

    /**
     * 根据certifyId查询认证详细信息
     * @param certifyId
     * @return
     */
    List<CifCertifyDetailModel> getCertifyDetailById(String certifyId);

    /**
     * 根据certifyId和认证类型查询认证详细信息
     * @param certifyChannel
     * @param certifyId
     * @return
     */
    List<CifCertifyDetailModel> getCertifyDetailByChannel(String certifyChannel, String certifyId);

    /**
     * 查询认证渠道数
     * @param certifyId
     * @return
     */
    int getCertifyCount(String certifyId);

    /**
     * 新增认证详细信息
     * @param cifCertifyDetailModel
     * @return
     */
    int insertCertifyDetail(CifCertifyDetailModel cifCertifyDetailModel);
}
