/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.learn.domain.SpringBootResult;

/**
 * 统一异常处理类
 * @author liuzq
 * @version $Id: GlobalExceptionHandler.java, v 0.1 2016年5月5日 下午5:16:03 liuzq Exp $
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public SpringBootResult myExceptionHandler(HttpServletRequest req,
                                               MyException e) throws Exception {
        SpringBootResult result = new SpringBootResult();
        result.setSuccess(false);
        result.setResultCode("error");
        result.setResultMsg(e.getMessage() + ":" + req.getRequestURL());
        log.error("请求地址:" + req.getRequestURL());
        log.error("错误信息:=====", e);
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public SpringBootResult exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        SpringBootResult result = new SpringBootResult();
        result.setSuccess(false);
        result.setResultCode("error");
        result.setResultMsg("系统错误!" + req.getRequestURL());
        log.error("请求地址:" + req.getRequestURL());
        log.error("错误信息:=====", e);
        return result;
    }
}
