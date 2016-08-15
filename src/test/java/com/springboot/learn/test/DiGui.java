/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

/**
 * 第1个人10，第2个比第1个人大2岁，依次递推，请用递归方式计算出第8个人多大
 * @author liuzq
 * @version $Id: DiGui.java, v 0.1 2016年8月15日 下午4:31:31 liuzq Exp $
 */
public class DiGui {
    public static void main(String[] args) {
        System.out.println(computeAge(8));
    }

    public static int computeAge(int n) {
        if (n == 1)
            return 10;
        return computeAge(n - 1) + 2;
    }

    public static void toBinary(int n, StringBuffer result) {
        if (n / 2 != 0)
            toBinary(n / 2, result);
        result.append(n % 2);
    }
}
