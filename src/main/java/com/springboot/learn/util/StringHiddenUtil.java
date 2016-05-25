/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;

/**
 * 敏感信息处理工具
 * @author liuzq
 * @version $Id: StringHiddenUtil.java, v 0.1 2016年4月27日 上午11:26:44 liuzq Exp $
 */
public class StringHiddenUtil {

    /*** 处理关键字段的隐藏字符*/
    private static final char   SUBSTITUE_CHARACTOR   = '*';

    /*** 标志空字符串*/
    private static final String DEFAULT_NULL_STRING   = "NULL";

    /*** 标志处理异常*/
    private static final String PROC_EXCEPTION_STRING = "EXCEPTION";

    /** 默认的前几位隐藏字符 */
    private static final int    DEFAULT_START         = 3;

    /** 默认的后几位隐藏字符 */
    private static final int    DEFAULT_END           = 4;

    /**用于存放过滤规则列表*/
    public static List<String>  REG_RULES             = new ArrayList<String>();

    /**扩展过滤规则在这里*/
    static {
        /**身份证ID 18位或15位*/
        REG_RULES.add("(\\d{18}|\\d{17}(\\d|X|x)|\\d{15})");
        /**手机号正则表达式*/
        REG_RULES.add("(1[3,5,6,8,4,7]\\d{9})");
    }

    /**正则表达式组合-GROUP1-开始符*/
    public static final String REG_GROUP_ONE = "(^|\\W|\\s)";

    /**正则表达式组合-GROUP2-匹配的敏感信息*/
    public static String       REG_GROUP_TWO = "";

    /**初始化正则表达式的所有规则*/
    static {

        if (!CollectionUtils.isEmpty(REG_RULES)) {

            REG_GROUP_TWO += "(";

            int ruleSize = REG_RULES.size();
            if (ruleSize > 1) {

                for (int i = 0; i < ruleSize - 1; i++) {
                    REG_GROUP_TWO += REG_RULES.get(i) + "|";
                }
                REG_GROUP_TWO += REG_RULES.get(ruleSize - 1);

            }
            //只有一条正则表达式
            else if (ruleSize == 1) {
                REG_GROUP_TWO = REG_RULES.get(0);
            }

            REG_GROUP_TWO += ")";
        }

    }

    /**正则表达式组合-GROUP3-结束符*/
    public static final String REG_GROUP_THREE = "($|\\W|\\s)";

    /**正则表达式组合-GROUP0*/
    public static final String REG_GROUP       = REG_GROUP_ONE + REG_GROUP_TWO + REG_GROUP_THREE;

    /**
     * 按照前六后四，中间星号的方式对敏感信息进行隐藏。如果<code>info</code>
     * <li>不足7位则返回一个内容为后四位为明文的字符串。
     * <li>如果为空，则返回NULL
     * 
     * 超过7位<li>13813813826->138****3826</li>
     * 刚好为7位<li>1234567->***4567</li>
     * 小于7位，大于4位<li>123456->**3456</li>
     * 小于等于4位<li>1234->1234</li>
     * 
     * @param info          需要隐藏的字符串
     * @return String       已经实现隐藏的字符串
     */
    public static String hiddenSensitiveInfo(String info) {
        return hiddenSensitiveInfo(info, DEFAULT_START, DEFAULT_END);
    }

    /**
     * 过滤敏感信息
     * 
     * <br>当前只过滤身份证号和手机号
     * <br>需要扩充，在REG_GROUP中添加正则表达式
     * 
     * <li>phoneNo:13813813826                      -->     phoneNo:138****3826
     * <li>certId:500227198809285019                -->     certId:500***********5019
     * <li>orderId=2014032511001001360000471021     -->     orderId=2014032511001001360000471021
     * 
     * @param info  整体字符串
     * @return      过滤隐藏后的字符串
     */
    public static String filterSensitiveInfo(String info) {

        //~空处理
        if (StringUtil.isBlank(info)) {
            return info;
        }

        Pattern pattern = Pattern.compile(REG_GROUP);
        Matcher matcher = pattern.matcher(info);

        String after = info;

        //~正则匹配
        while (matcher.find()) {

            String target = matcher.group(2);//获取敏感信息,REG_GROUP第3个group,index为2
            after = StringUtil.replace(after, target, hiddenSensitiveInfo(target));

        }

        return after;
    }

    /**
     * <pre>
     *  敏感信息处理,例如信用卡号和身份证号码都进行前六后四显示
     *  如果<code>info</code>长度不足则全部视为敏感信息进行过滤。
     * </pre>
     * @param info                                  敏感信息
     * @param visibleCharFromStart                  前几位明文显示
     * @param visibleCharFromEnd                    后几位明文显示
     * @return String                               处理之后的String
     */
    public static String hiddenSensitiveInfo(String info, int visibleCharFromStart,
                                             int visibleCharFromEnd) {
        try {

            if (info == null) {
                return DEFAULT_NULL_STRING;
            }

            String _info = StringUtil.trim(info);
            if (_info.length() <= visibleCharFromEnd) {
                return _info;
            }

            int canShowLen = visibleCharFromStart + visibleCharFromEnd;
            StringBuilder sb = new StringBuilder();

            if (_info.length() > visibleCharFromEnd && _info.length() <= canShowLen) {

                sb.append(StringUtil.alignLeft("", _info.length() - visibleCharFromEnd,
                    SUBSTITUE_CHARACTOR));
                sb.append(StringUtil.right(_info, visibleCharFromEnd));
                return sb.toString();
            }

            if (_info.length() > canShowLen) {
                sb.append(StringUtil.left(_info, visibleCharFromStart));
                sb.append(
                    StringUtil.alignLeft("", _info.length() - canShowLen, SUBSTITUE_CHARACTOR));
                sb.append(StringUtil.right(_info, visibleCharFromEnd));
                return sb.toString();
            }

            return sb.toString();
        } catch (Exception e) {
            return PROC_EXCEPTION_STRING;
        }
    }

    /**
     * 过滤敏感数字信息
     * 
     * <br>过滤连续的N个以上的数字,隐藏中间的50%
     * 
     * <li>phoneNo:13813813826                      -->     phoneNo:138****3826
     * <li>certId:500227198809285019                -->     certId:500***********5019
     * <li>orderId=2014032511001001360000471021     -->     orderId=2014032**************0471021
     * 
     * @param info  整体字符串
     * @param continuousCount 连续的数字个数
     * @return      过滤隐藏后的字符串
     */
    public static String filterSensitiveInfoForContinuousNum(String info, int continuousCount) {
        //~空处理
        if (StringUtil.isBlank(info)) {
            return info;
        }
        String regGroupTwo = "(\\d{" + continuousCount + ",})";
        String regGroup = REG_GROUP_ONE + regGroupTwo + REG_GROUP_THREE;
        Pattern pattern = Pattern.compile(regGroup);
        Matcher matcher = pattern.matcher(info);

        String after = info;

        //~正则匹配
        while (matcher.find()) {

            String target = matcher.group(2);//获取敏感信息,REG_GROUP第3个group,index为2
            int length = target.length();

            //System.out.println("原始字符串为："+target);
            //System.out.println("总长度为："+length);

            int lengthTemp = 0;
            //奇数 length+1
            if (length % 2 != 0) {
                lengthTemp = length + 1;
            } else {
                lengthTemp = length;
            }
            //隐藏的位数
            int hiddenCount = lengthTemp / 2;
            //System.out.println("隐藏的位数为："+hiddenCount+"位");

            int headNum = (int) Math.ceil((length - hiddenCount) / 2.0);

            // System.out.println("前面显示："+headNum+"位");
            int tailNum = (int) Math.floor((length - hiddenCount) / 2.0);

            after = StringUtil.replaceOnce(after, target,
                hiddenSensitiveInfo(target, headNum, tailNum));
            //System.out.println("隐藏结果："+after);
        }
        return after;
    }

}
