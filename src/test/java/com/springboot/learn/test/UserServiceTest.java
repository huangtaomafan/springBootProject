/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.learn.SpringBootLearnApplication;
import com.springboot.learn.domain.User;
import com.springboot.learn.service.UserService;

/**
 * UserService测试类
 * @author liuzq
 * @version $Id: UserMapperTest.java, v 0.1 2016年4月27日 下午6:54:40 liuzq Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootLearnApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userSerivce;

    @Test
    public void testSelect() throws Exception {
        User user = new User();
        user.setId(6l);
        //        user.setName("唐金泽");
        //        user.setTelNo("18600030004");
        List<User> users = userSerivce.selectUser(user);
        System.out.println(users);
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 4l;
        int i = userSerivce.deleteUser(id);
        System.out.println(i);
    }

    @Test
    @Transactional
    public void testInsert() throws Exception {
        User user = new User();
        user.setName("gwhw4");
        user.setAge(29);
        user.setSex("男");
        user.setTelNo("13456789615");
        user.setPassword("gqgewqgq");
        user.setGmtCreate(new Date());
        user.setGmtModify(new Date());
        int i = userSerivce.insertUser(user);
        System.out.println(i);
        System.out.println(user.getId());
        
        User user2 = new User();
        user2.setName("gwhwhw3423");
        user2.setAge(29);
        user2.setSex("男");
//        user2.setTelNo("13452789265");
        user2.setPassword("gqgewqgq");
        user2.setGmtCreate(new Date());
        user2.setGmtModify(new Date());
        int j = userSerivce.insertUser(user2);
        System.out.println(j);
        System.out.println(user2.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setName("王雪莹");
        user.setAge(28);
        user.setSex("女");
        user.setTelNo("18600030006");
        user.setPassword("123abc123");
        user.setGmtModify(new Date());
        user.setId(8l);
        int i = userSerivce.updateUser(user);
        System.out.println(i);
    }
}
