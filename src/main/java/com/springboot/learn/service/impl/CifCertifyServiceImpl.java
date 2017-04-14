/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.springboot.learn.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.learn.domain.CifCertifyDetailModel;
import com.springboot.learn.domain.CifCertifyInfoModel;
import com.springboot.learn.mapper.CifCertifyDetailMapper;
import com.springboot.learn.mapper.CifCertifyInfoMapper;
import com.springboot.learn.service.CifCertifyService;
import com.springboot.learn.util.ExcelUtil;
import com.springboot.learn.util.StringUtil;

/**
 * 实现类
 * @author 刘志强
 * @version $Id: CifCertifyServiceImpl.java, v 0.1 2017年4月14日 上午11:12:26 刘志强 Exp $
 */
@Service
public class CifCertifyServiceImpl implements CifCertifyService {

    @Autowired
    private CifCertifyDetailMapper cifCertifyDetailMapper;

    @Autowired
    private CifCertifyInfoMapper   cifCertifyInfoMapper;

    @Value("${excel.name}")
    private String                 excelName;

    /** 
     * @see com.springboot.learn.service.CifCertifyService#doTrans()
     */
    @Override
    public void doTrans() {
        try {
            System.out.println("开始数据迁移...");
            if (StringUtil.equals(excelName, "certifyInfo.xlsx")) {
                doCertifyInfo();
            } else {
                doCertifyDetail();
            }
            System.out.println("完成数据迁移!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 迁移认证信息
     */
    private void doCertifyInfo() {
        System.out.println("开始迁移认证信息...");
        ExcelUtil excelUtil = new ExcelUtil("/home/deploy/certifyInfo.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        Timestamp tm = new Timestamp(new Date().getTime());
        CifCertifyInfoModel infoModel = new CifCertifyInfoModel();
        for (String[] strs : result) {
            infoModel.setCertifyId(strs[0]);
            infoModel.setRealName(strs[2]);
            infoModel.setMobile(strs[3]);
            infoModel.setGmtModified(tm);
            //新增/修改认证信息
            CifCertifyInfoModel cifCertifyInfoModel = cifCertifyInfoMapper
                .getCertifyInfoById(strs[2]);
            if (null == cifCertifyInfoModel) {
                infoModel.setCertType("01");
                infoModel.setCertNo(strs[1]);
                infoModel.setGmtCreate(tm);
                cifCertifyInfoMapper.insertCertifyInfo(infoModel);
            } else {
                cifCertifyInfoMapper.updateCertifyInfo(infoModel);
            }
        }
        System.out.println("完成迁移认证信息!");
    }

    /**
     * 迁移认证详情
     */
    private void doCertifyDetail() {
        System.out.println("开始迁移认证详情...");
        ExcelUtil excelUtil = new ExcelUtil("/home/deploy/" + excelName);
        List<String[]> result = excelUtil.getAllData(0);
        Timestamp tm = new Timestamp(new Date().getTime());
        CifCertifyDetailModel detailModel = new CifCertifyDetailModel();
        for (String[] strs : result) {
            detailModel.setCertifyId(strs[0]);
            detailModel.setCertifyChannel(transCertifyChannel(strs[1]));
            detailModel.setCertifyType(strs[2]);
            detailModel.setGmtCreate(tm);
            //新增认证详情
            cifCertifyDetailMapper.insertCertifyDetail(detailModel);
        }
        System.out.println("完成迁移认证详情!");
    }

    /**
     * 认证渠道转换
     * @param string
     * @return
     */
    private String transCertifyChannel(String string) {

        String certifyChannel = "";
        if ("02".equals(string)) {
            certifyChannel = "SMKCARD_1";
        } else if ("03".equals(string)) {
            certifyChannel = "SMKCARD_2";
        } else if ("04".equals(string)) {
            certifyChannel = "SMKCARD_3";
        } else if ("05".equals(string)) {
            certifyChannel = "SMKCARD_4";
        } else if ("06".equals(string)) {
            certifyChannel = "SMKCARD_7";
        } else if ("07".equals(string)) {
            certifyChannel = "SMKCARD_8";
        } else if ("01".equals(string)) {
            certifyChannel = "BANKCARD";
        } else {
            certifyChannel = StringUtil.toUpperCase(string);
        }
        return certifyChannel;
    }

}
