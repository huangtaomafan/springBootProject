/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springboot.learn.domain.SpringBootResult;
import com.springboot.learn.util.DateUtil;

/**
 * 上传附件controller
 * @author liuzq
 * @version $Id: UploadFileController.java, v 0.1 2016年5月30日 下午3:58:54 liuzq Exp $
 */
@Controller
public class UploadFileController extends BaseController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    /**
     * 页面初始化
     * @param user
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String selectUser(ModelMap modelMap) throws IOException {
        List<String> list = new ArrayList<>();
        //获取类路径下资源文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:static/images/*");
        for (Resource resource : resources) {
            System.out.println(resource.getFile().getName());
            list.add("/images/" + resource.getFile().getName());
        }
        modelMap.addAttribute("pictures", list);
        return "uploadFile";
    }

    /**
     * 处理上传附件
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public SpringBootResult selectUser(HttpServletRequest request) {
        SpringBootResult result = new SpringBootResult();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String path = "src\\main\\resources\\static\\images\\";
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            String name = file.getOriginalFilename();
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        new File(path + DateUtil.getLongDateString(new Date()) + name)));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    logger.error("写入异常", e);
                    return result;
                }
            } else {
                return result;
            }
        }
        result.setSuccess(true);
        return result;
    }
}
