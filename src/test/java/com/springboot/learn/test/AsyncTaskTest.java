/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.learn.SpringBootLearnApplication;
import com.springboot.learn.async.AsyncTask;

/**
 * AsyncTask测试类
 * @author liuzq
 * @version $Id: AsyncTaskTest.java, v 0.1 2016年5月26日 上午10:28:27 liuzq Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootLearnApplication.class)
public class AsyncTaskTest {

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testSync() throws Exception {
        long start = System.currentTimeMillis();
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @Test
    public void testAsync() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncTask.asyncTaskOne();
        Future<String> task2 = asyncTask.asyncTaskTwo();
        Future<String> task3 = asyncTask.asyncTaskThree();
        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("异步任务全部完成，总耗时：" + (end - start) + "毫秒");

    }
}
