/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;

/**
 * 安全相关工具类
 * @author liuzq
 * @version $Id: SecurityUtil.java, v 0.1 2016年11月24日 下午8:12:09 liuzq Exp $
 */
public class SecurityUtil {

    /**
     * Verify if a byte[]'s HASH value is equals the specified one.
     * 
     * @param aAlgorithm Message digest algorithm
     * @param aTobeVerified 
     * @param aHashArray
     * 
     * @return boolean if equals, return true, otherwise, return false.
     */
    public static boolean verifyHash(String aAlgorithm, byte[] aTobeVerified,
                                     byte[] aHashArray) throws Exception {
        byte[] tHashValue = hash(aAlgorithm, aTobeVerified);
        String tHashValueStr = Base64Util.encode(tHashValue);
        String tHashArrayStr = Base64Util.encode(aHashArray);
        if (tHashValueStr.equals(tHashArrayStr)) {
            return true;
        }
        return false;
    }

    /**
     * 根据指定的算法，计算传入数据的摘要值
     * 
     * @param aAlgorithm 摘要算法
     * @param aByteArray 需要计算摘要的数据
     * @return 摘要值
     * @throws NoSuchAlgorithmException 
     */
    public static byte[] hash(String aAlgorithm, byte[] aByteArray) throws Exception {
        MessageDigest md = MessageDigest.getInstance(aAlgorithm);
        md.update(aByteArray);
        return md.digest();
    }

    /**
     * 根据指定的算法，计算传入数据的摘要值
     * 
     * @param aAlgorithm 摘要算法
     * @param aByteArray 需要计算摘要的数据
     * @return String 摘要值
     * @throws NoSuchAlgorithmException 
     */
    public static String hashWithBase64Encoded(String aAlgorithm,
                                               byte[] aByteArray) throws Exception {
        return Base64Util.encode(hash(aAlgorithm, aByteArray));
    }

    /**建行专用对MAC压码后的字符进行显示方式的转化<br>
     * 1、对128位的交易结果按4位为一个单位进行划分，共获得32段<br>
     * 2、将每段看成一个16进制数，如0011为0X3，1101为0Xd。<br>
     * 3、将这个数映射到ASCII码表，形成相应的字符，如0X2为“2”，0Xd为“d”。<br>
     * 4、将这些字符连成一个字符串，长度为32。
     * @param aAlgorithm 摘要算法
     * @param aByteArray 需要计算摘要的数据
     * @return String 摘要值
     * @throws Exception
     */
    public static String hashWithCCB(String aAlgorithm, byte[] aByteArray) throws Exception {
        int len, i;
        byte tb;
        char high, tmp, low;
        byte[] aMACByteArray = hash(aAlgorithm, aByteArray);
        String result = new String();
        len = aMACByteArray.length;
        for (i = 0; i < len; i++) {
            tb = aMACByteArray[i];

            tmp = (char) ((tb >>> 4) & 0x000f);
            if (tmp >= 10)
                high = (char) ('a' + tmp - 10);
            else
                high = (char) ('0' + tmp);
            result += high;
            tmp = (char) (tb & 0x000f);
            if (tmp >= 10)
                low = (char) ('a' + tmp - 10);
            else
                low = (char) ('0' + tmp);

            result += low;
        }
        return result;

    }

    /**
     * 
     * @Title: base64dec
     * @Description:  解析base64
     * @author yang_df
     * @since 2014年11月27日
     * @param base64
     * @return
     * @throws IOException
     */
    public static String base64dec(String base64) throws IOException {
        return new String(new BASE64Decoder().decodeBuffer(base64));
    }

}
