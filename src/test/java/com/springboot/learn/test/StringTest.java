/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

/**
 * 输入为一个字符串和字 、编写一个截取字符串的函数
 * 输出为按字节截取的字符串，但要保证汉字不被截取半个，
 * @author liuzq
 * @version $Id: StringTest.java, v 0.1 2016年8月15日 下午1:44:20 liuzq Exp $
 */
public class StringTest {
    public static void main(String[] args) throws Exception {
        String str = "我 a 爱中华 abc 我爱传智 def";
        //        String str = "我 ABC 汉";
        int num = trimGBK(str.getBytes("GBK"), 5);
        System.out.println(str.substring(0, num));
        getCount();
    }

    public static int trimGBK(byte[] buf, int n) {
        int num = 0;
        boolean bChineseFirstHalf = false;
        for (int i = 0; i < n; i++) {
            if (buf[i] < 0 && !bChineseFirstHalf) {
                bChineseFirstHalf = true;
            } else {
                num++;
                bChineseFirstHalf = false;
            }
        }
        return num;
    }

    public static void getCount() {
        String str = "aaaabbc 中国1512";
        int engishCount = 0;
        int chineseCount = 0;
        int digitCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                digitCount++;
            } else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                engishCount++;
            } else {
                chineseCount++;
            }
        }
        System.out.println("engishCount:" + engishCount);
        System.out.println("chineseCount:" + chineseCount);
        System.out.println("digitCount:" + digitCount);
    }
}
