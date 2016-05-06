/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.learn.domain.User;

/**
 * user数据库操作接口
 * @author liuzq
 * @version $Id: UserMapper.java, v 0.1 2016年4月28日 下午5:22:45 liuzq Exp $
 */
@Mapper
public interface UserMapper {

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
