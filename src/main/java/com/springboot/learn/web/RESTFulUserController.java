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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.learn.domain.SpringBootResult;
import com.springboot.learn.domain.User;
import com.springboot.learn.exception.MyException;
import com.springboot.learn.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * RESTFul的UserController
 * @author liuzq
 * @version $Id: UserController.java, v 0.1 2016年4月27日 下午1:58:33 liuzq Exp $
 */
@RestController
@RequestMapping(value = "/restfulUser")
public class RESTFulUserController extends BaseController {

    @Autowired
    private UserService         userSerivce;

    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(RESTFulUserController.class);

    /**
     * 根据条件动态查询用户
     * @param user
     * @return
     */
    @ApiOperation(value = "查询用户", notes = "根据id、name、telNo的值动态查询用户，如果都为空则查询全部用户")
    @RequestMapping(value = "/selectUser", method = { RequestMethod.GET })
    public List<User> selectUser(@ModelAttribute User user) {
        List<User> users = userSerivce.selectUser(user);
        return users;
    }

    /**
     * restful根据id查询用户
     * @param user
     * @return
     */
    @ApiOperation(value = "查询用户", notes = "restful根据id查询用户")
    @RequestMapping(value = "/selectUser/{id}", method = RequestMethod.GET)
    public SpringBootResult selectUser(@PathVariable Long id) {
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
     * 修改用户
     * @param modelMap
     * @param user
     */
    @ApiOperation(value = "修改用户", notes = "修改用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public SpringBootResult updateUser(@RequestBody User user) {
        SpringBootResult result = new SpringBootResult();
        user.setGmtModify(new Date());
        int i = userSerivce.updateUser(user);
        result.setData(i);
        result.setSuccess(true);
        return result;
    }

    /**
     * 新增用户
     * @param modelMap
     * @param user
     */
    @ApiOperation(value = "新增用户", notes = "新增用户信息提交")
    @RequestMapping(value = "/insertUser", method = { RequestMethod.PUT })
    public SpringBootResult insertUser(@RequestBody User user) {
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
    @ApiOperation(value = "删除用户", notes = "修改id删除用户")
    @RequestMapping(value = "/deleteUser", method = { RequestMethod.DELETE })
    public SpringBootResult deleteUser(@RequestParam Long id) {
        SpringBootResult result = new SpringBootResult();
        int i = userSerivce.deleteUser(id);
        result.setData(i);
        result.setSuccess(true);
        return result;
    }

}
