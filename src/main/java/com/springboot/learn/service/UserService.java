/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.service;

import java.util.List;

import com.springboot.learn.domain.User;

/**
 * User操作接口
 * @author liuzq
 * @version $Id: UserService.java, v 0.1 2016年4月27日 下午6:44:46 liuzq Exp $
 */
public interface UserService {

    /**
     * 查询用户(根据user的字段动态判断查询)
     * @param user
     * @return
     */
    public List<User> selectUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    public int deleteUser(Long id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(User user);
}
