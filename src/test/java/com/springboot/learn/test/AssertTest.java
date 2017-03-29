/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

/**
 * 
 * @author liuzq
 * @version $Id: AssertTest.java, v 0.1 2016年8月15日 上午11:23:46 liuzq Exp $
 */
public class AssertTest {

    public static void main(String[] args) {
        int i = 0;
        for (i = 0; i < 5; i++) {
            System.out.println(i);
        }
        //假设程序不小心多了一句--i;
        --i;
        assert i == 5;
    }
}
