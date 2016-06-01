/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.learn.util.FileUtil;

/**
 * 基于注解的spring拦截器
 * @author liuzq
 * @version $Id: MyInterceptor.java, v 0.1 2016年6月1日 上午10:29:18 liuzq Exp $
 */
public class MyInterceptor implements HandlerInterceptor {

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                                Exception arg3) throws Exception {
        System.err.println("HandlerInterceptor afterCompletion!");
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                           ModelAndView arg3) throws Exception {
        System.err.println("HandlerInterceptor postHandle!");
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        System.err.println("HandlerInterceptor preHandle!");
        //获取上传的文件,校验后缀名
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        boolean flag = false;
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()) {
                flag = true;
                String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
                if ("txt,xls,xlsx,doc,docx,ppt,pptx,jpg,jpeg,png,bmp,gif".indexOf(suffix) == -1) {
                    throw new MultipartException("文件格式不支持");
                }
            }
        }
        if (!flag) {
            throw new MultipartException("请选择上传的文件!");
        }
        return true;
    }

}
