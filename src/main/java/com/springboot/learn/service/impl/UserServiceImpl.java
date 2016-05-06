/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.learn.domain.User;
import com.springboot.learn.mapper.UserMapper;
import com.springboot.learn.service.UserService;

/**
 * UserService实现类
 * @author liuzq
 * @version $Id: UserServiceImpl.java, v 0.1 2016年4月27日 下午6:48:02 liuzq Exp $
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /** 
     * @see com.springboot.learn.service.UserService#selectUser(com.springboot.learn.domain.User)
     */
    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }

    /** 
     * @see com.springboot.learn.service.UserService#deleteUser(java.lang.Long)
     */
    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }

    /** 
     * @see com.springboot.learn.service.UserService#insertUser(com.springboot.learn.domain.User)
     */
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    /** 
     * @see com.springboot.learn.service.UserService#updateUser(com.springboot.learn.domain.User)
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

}
