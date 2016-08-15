/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.util.Arrays;

/**
 * 有数组 a[n]，用 java代码将数组元素顺序颠倒
 * @author liuzq
 * @version $Id: SwapDemo.java, v 0.1 2016年8月15日 下午4:40:38 liuzq Exp $
 */
public class SwapDemo {
    public static void main(String[] args) {
        int[] a = new int[] { (int) (Math.random() * 1000), (int) (Math.random() * 1000),
                              (int) (Math.random() * 1000), (int) (Math.random() * 1000),
                              (int) (Math.random() * 1000) };
        System.out.println(a);
        System.out.println(Arrays.toString(a));
        swap(a);
        System.out.println(Arrays.toString(a));
    }

    public static void swap(int a[]) {
        int len = a.length;
        for (int i = 0; i < len / 2; i++) {
            int tmp = a[i];
            a[i] = a[len - 1 - i];
            a[len - 1 - i] = tmp;
        }
    }
}
