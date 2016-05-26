/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.async;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 异步任务
 * @author liuzq
 * @version $Id: AsyncTask.java, v 0.1 2016年5月26日 上午10:21:29 liuzq Exp $
 */
@Component
public class AsyncTask {

    public static Random random = new Random();

    public void doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    public void doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    public void doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
    }

    @Async
    public Future<String> asyncTaskOne() throws Exception {
        System.out.println("异步开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("异步完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("异步任务一完成");
    }

    @Async
    public Future<String> asyncTaskTwo() throws Exception {
        System.out.println("异步开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("异步完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("异步任务二完成");
    }

    @Async
    public Future<String> asyncTaskThree() throws Exception {
        System.out.println("异步开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("异步完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("异步任务三完成");
    }
}
