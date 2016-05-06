/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.learn.domain.SpringBootResult;
import com.springboot.learn.domain.User;
import com.springboot.learn.exception.MyException;
import com.springboot.learn.service.UserService;

/**
 * UserController
 * @author liuzq
 * @version $Id: UserController.java, v 0.1 2016年4月27日 下午1:58:33 liuzq Exp $
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService         userSerivce;

    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 根据条件动态查询用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/selectUser", method = { RequestMethod.POST, RequestMethod.GET })
    public String selectUser(ModelMap modelMap, User user) {
        List<User> users = userSerivce.selectUser(user);
        modelMap.put("users", users);
        return "userList";
    }

    /**
     * restful根据id查询用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectUser/{id}", method = RequestMethod.GET)
    public SpringBootResult selectUser(ModelMap modelMap, @PathVariable Long id) {
        log.info("用户id:" + id);
        if (id == 1) {
            throw new MyException("id为空");
        }
        SpringBootResult result = new SpringBootResult();
        User user = new User();
        user.setId(id);
        List<User> users = userSerivce.selectUser(user);
        result.setData(users.get(0));
        result.setSuccess(true);
        return result;
    }

    /**
     * 修改用户页面初始化
     * @param modelMap
     * @param user
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String updateUser(ModelMap modelMap, @RequestParam Long id) {
        User user = new User();
        user.setId(id);
        List<User> users = userSerivce.selectUser(user);
        modelMap.put("user", users.get(0));
        return "updateUser";
    }

    /**
     * 修改用户
     * @param modelMap
     * @param user
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public SpringBootResult updateUser(ModelMap modelMap, User user) {
        SpringBootResult result = new SpringBootResult();
        user.setGmtModify(new Date());
        int i = userSerivce.updateUser(user);
        result.setData(i);
        result.setSuccess(true);
        return result;
    }

    /**
     * 新增用户页面初始化
     * @param modelMap
     * @param user
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public String insertUser(ModelMap modelMap) {
        return "insertUser";
    }

    /**
     * 新增用户
     * @param modelMap
     * @param user
     */
    @ResponseBody
    @RequestMapping(value = "/insertUser", method = { RequestMethod.PUT })
    public SpringBootResult insertUser(ModelMap modelMap, @ModelAttribute User user) {
        SpringBootResult result = new SpringBootResult();
        user.setGmtCreate(new Date());
        user.setGmtModify(new Date());
        int i = userSerivce.insertUser(user);
        result.setData(i + "," + user.getId());
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据id删除用户
     * @param modelMap
     * @param id
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = { RequestMethod.DELETE, RequestMethod.GET })
    public SpringBootResult deleteUser(ModelMap modelMap, @RequestParam Long id) {
        SpringBootResult result = new SpringBootResult();
        int i = userSerivce.deleteUser(id);
        result.setData(i);
        result.setSuccess(true);
        return result;
    }

}
