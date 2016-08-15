/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

/**
 * 选择排序
 * @author liuzq
 * @version $Id: ChoiceSort.java, v 0.1 2016年8月15日 下午4:54:15 liuzq Exp $
 */
public class ChoiceSort {
    public static void _choiceSort(int[] a) {
        if (a == null || a.length <= 0) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            int min = i; /* 将当前下标定义为最小值下标 */

            for (int j = i + 1; j < a.length; j++) {
                if (a[min] > a[j]) { /* 如果有小于当前最小值的关键字 */
                    min = j; /* 将此关键字的下标赋值给min */
                }
            }
            if (i != min) {/* 若min不等于i，说明找到最小值，交换 */
                int tmp = a[min];
                a[min] = a[i];
                a[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17,
                    18, 23, 34, 15, 35, 25, 53, 51 };
        ChoiceSort._choiceSort(a);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
}
