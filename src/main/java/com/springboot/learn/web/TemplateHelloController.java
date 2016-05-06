/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * thymeleaf模板controller
 * @author liuzq
 * @version $Id: TemplateHelloController.java, v 0.1 2016年4月27日 下午3:49:13 liuzq Exp $
 */
@Controller
public class TemplateHelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "欢迎！！");
        map.addAttribute("path", "/images/1.jpg");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}
