/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.springboot.learn.domain.SpringBootResult;
import com.springboot.learn.domain.User;
import com.springboot.learn.exception.MyException;
import com.springboot.learn.service.UserService;
import com.springboot.learn.util.CommonValidateUtil;
import com.springboot.learn.util.StringUtil;

/**
 * SpringBoot第一个controller
 * @author liuzq
 * @version $Id: HelloController.java, v 0.1 2016年4月27日 上午10:59:07 liuzq Exp $
 */
@Controller
public class HelloController {

    @Autowired
    private UserService userSerivce;

    @ResponseBody
    @RequestMapping("/hello")
    public SpringBootResult index() {
        SpringBootResult result = new SpringBootResult();
        result.setData("Hello World！");
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap modelMap, WebRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        //校验手机号格式是否正确
        if (!CommonValidateUtil.isValidMobile(userName)) {
            throw new MyException("手机号格式不正确:" + userName);
        }
        //因为手机号唯一,防止sql注入,先根据手机号查询出用户再比对密码
        User user = new User();
        user.setTelNo(userName);
        List<User> users = userSerivce.selectUser(user);
        if (users.size() <= 0) {
            throw new MyException("用户名不正确:" + userName);
        }
        if (!StringUtil.equals(password, users.get(0).getPassword())) {
            throw new MyException("密码不正确!");
        }
        modelMap.put("users", users);
        return "userList";
    }
}
