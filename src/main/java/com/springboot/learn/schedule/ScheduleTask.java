/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.schedule;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.learn.util.DateUtil;

/**
 * 定时任务
 * @author liuzq
 * @version $Id: ScheduleTask.java, v 0.1 2016年5月25日 下午4:47:30 liuzq Exp $
 */
@Component
public class ScheduleTask {

    /**
     * 每5秒打印一次当前时间
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + DateUtil.getNewFormatDateString(new Date()));
    }
}
