/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验格式工具类
 * @author liuzq
 * @version $Id: CommonValidateUtil.java, v 0.1 2016年10月10日 上午11:36:55 liuzq Exp $
 */
public class CommonValidateUtil {

    /**国内手机格式的正则表达式*/
    public final static String REGEX_MOBILE            = "^[1](3|4|5|7|8)[0-9]{9}$";

    /**国外手机格式的正则表达式*/
    public final static String REGEX_FOREIGN_MOBILE    = "^(00)?\\d{1,3}\\-\\d{6,13}$";

    /**Email格式的正则表达式*/
    public final static String REGEX_EMAIL             = "^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,20}$";

    /**支付宝登录号的正则表达式*/
    public final static String REGEX_LOGON_ID          = "^((([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,20})|([1](3|4|5|7|8)[0-9]{9})|((00)?\\d{1,3}\\-\\d{6,13}))$";
    /**手机6位数字校验码*/
    public final static String REGUX_VALIDATE_CODE     = "^\\d{6}$";

    /**16位纯数字流水号*/
    public final static String REGEX_VALIDATE_BIZ_NO   = "^\\d{16}$";

    /**4位图片校验码*/
    public final static String REGEX_PIC_CODE          = "^\\d{4}$";

    /**电话号码，只能由数字和"-"组成,6到20位*/
    public final static String REGEX_PHONE             = "^[0-9,-]{6,20}$";

    /**邮政编码,只能6位数字*/
    public final static String POST                    = "^\\d{1,6}$";

    /**用户姓名,只能由中文汉字、大写英文字母或.构成*/
    public final static String REGEX_USER_NAME         = "^([\u4e00-\u9fa5|A-Z]+\\s*\\.?\\s*)+[\u4e00-\u9fa5|A-Z]$";

    /**纯数字*/
    public final static String NUM                     = "^(\\d)+$";

    /**金额，类似于-135,342,12.954 */
    public final static String REGEX_VALIDATE_MONEY    = "^[+-]?\\d+(,\\d{3})*(\\.\\d+)?$";

    /**手机动态口令，可以不输入，输入长度只能数字长度6位 */
    public final static String MOBILE_PROTECT_ACK_CODE = "^\\d{0}|\\d{6}$";

    /**替身邮的正则表达式*/
    public final static String REGEX_SUBSTITUTE_EMAIL  = "^((.*)\\.{2,}(.*)@gmail.com)|((.*)-(.*)@((yahoo.com.cn)|(yahoo.cn)|(ymail.com)|(rocketmail.com)|(tianya.cn)))$";

    /**URL校验*/
    public final static String REGEX_URL               = "((http[s]{0,1}://)|(HTTP[S]{0,1}://))[_a-zA-Z0-9\\:\\.\\/]*";

    /**小写字符串*/
    public final static String REGEX_LOWERCASE_CHAR    = "[a-z]+$";

    /**字符串数字校验*/
    public final static String REGEX_STR_NUM           = "[a-zA-Z0-9]+";

    /** 字符串、数字、下划线校验 */
    public final static String JSONOP_CALLBACK         = "^([a-zA-Z0-9_])*$";

    /** 红包模板URL验证正则表达式 */
    public final static String REGX_URL_COUPON         = "((http[s]{0,1}://)|(HTTP[S]{0,1}://))[_a-zA-Z0-9\\:\\.\\/\\?\\&\\=]*";

    /**证件有效期的正则表达式： YYYYMMDD */
    public final static String EXPIRY_DATE             = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-9]))))";

    public static enum RegisterSupportMobileArea {

        CN(1, "中国大陆", Pattern.compile("^(86){0,1}\\-?1[3,4,5,7,8]\\d{9}$"), "86","CN"),
        HK(2, "香港", Pattern.compile("^(852){1}\\-?0{0,1}[1,5,6,9](?:\\d{7}|\\d{8}|\\d{12})$"), "852","HK"),
        MO(3, "澳门", Pattern.compile("^(853){1}\\-?6\\d{7}$"), "853","MO"),
        TW(4, "台湾", Pattern.compile("^(886){1}\\-?0{0,1}[6,7,9](?:\\d{7}|\\d{8}|\\d{10})$"), "886","TW"),
        KR(5, "韩国", Pattern.compile("^(82){1}\\-?0{0,1}[7,1](?:\\d{8}|\\d{9})$"), "82","KR"),
        JP(6, "日本", Pattern.compile("^(81){1}\\-?0{0,1}[7,8,9](?:\\d{8}|\\d{9})$"), "81","JP"),
        US(7, "美国", Pattern.compile("^(1){1}\\-?\\d{10,12}$"),"1","US"),
        CA(8, "加拿大", Pattern.compile("^(1){1}\\-?\\d{10}$"), "1","CA"),
        GB(9, "英国", Pattern.compile("^(44){1}\\-?[347-9](\\d{8,9}|\\d{11,12})$"), "44","GB"),
        AU(10, "澳大利亚", Pattern.compile("^(61){1}\\-?4\\d{8,9}$"), "61","AU"),
        SG(11, "新加坡", Pattern.compile("^(65){1}\\-?[13689]\\d{6,7}$"), "65","SG"),
        MY(12, "马来西亚", Pattern.compile("^(60){1}\\-?1\\d{8}$"), "60","MY"),
        TH(13, "泰国", Pattern.compile("^(66){1}\\-?[13456789]\\d{7,8}$"), "66","TH"),
        VN(14, "越南", Pattern.compile("^(84){1}\\-?[1-9]\\d{6,9}$"), "84","VN"),
        PH(15, "菲律宾", Pattern.compile("^(63){1}\\-?[24579](\\d{7,9}|\\d{12})$"), "63","PH"),
        ID(16, "印度尼西亚", Pattern.compile("^(62){1}\\-?[2-9]\\d{7,11}$"), "62","ID"),
        DE(17, "德国", Pattern.compile("^(49){1}\\-?1(\\d{5,6}|\\d{9,12})$"), "49","DE"),
        IT(18, "意大利", Pattern.compile("^(39){1}\\-?[37]\\d{8,11}$"), "39","IT"),
        FR(19, "法国", Pattern.compile("^(33){1}\\-?[168](\\d{5}|\\d{7,8})$"), "33","FR"),
        RU(20, "俄罗斯", Pattern.compile("^(7){1}\\-?[13489]\\d{9,11}$"), "7","RU"),
        NZ(21, "新西兰", Pattern.compile("^(64){1}\\-?[278]\\d{7,9}$"), "64","NZ"),
        NL(22, "荷兰", Pattern.compile("^(31){1}\\-?6\\d{8}$"), "31","NL"),
        SE(23, "瑞典", Pattern.compile("^(46){1}\\-?[124-7](\\d{8}|\\d{10}|\\d{12})$"), "46","SE"),
        UA(24, "乌克兰", Pattern.compile("^(380){1}\\-?[3-79]\\d{8,9}$"), "380","UA"),
        AT(25, "奥地利", Pattern.compile("^(43){1}\\-\\d{7,11}"), "43","AT"),
        AR(26, "阿根廷", Pattern.compile("^(54){1}\\-\\d{7,11}"), "54", "AR"),
        BE(27, "比利时", Pattern.compile("^(32){1}\\-\\d{7,11}"), "32", "BE"),
        BR(28, "巴西", Pattern.compile("^(55){1}\\-\\d{7,11}"), "55", "BR"),
        BG(29, "保加利亚", Pattern.compile("^(359){1}\\-\\d{7,11}"), "359", "BG"),
        BY(30, "白俄罗斯", Pattern.compile("^(375){1}\\-\\d{7,11}"), "375", "BY"),
        BZ(31, "伯利兹", Pattern.compile("^(501){1}\\-\\d{7,11}"), "501", "BZ"),
        BS(32, "巴哈马", Pattern.compile("^(1242){1}\\-\\d{7,11}"), "1242", "BS"),
        CO(33, "哥伦比亚", Pattern.compile("^(57){1}\\-\\d{7,11}"), "57", "CO"),
        KH(34, "柬埔寨", Pattern.compile("^(855){1}\\-\\d{7,11}"), "855", "KH"),
        CL(35, "智利", Pattern.compile("^(56){1}\\-\\d{7,11}"), "56", "CL"),
        DK(36, "丹麦", Pattern.compile("^(45){1}\\-\\d{7,11}"), "45", "DK"),
        EG(37, "埃及", Pattern.compile("^(20){1}\\-\\d{7,11}"), "20", "EG"),
        EE(38, "爱沙尼亚", Pattern.compile("^(372){1}\\-\\d{7,11}"), "372", "EE"),
        FI(39, "芬兰", Pattern.compile("^(358){1}\\-\\d{7,11}"), "358", "FI"),
        GR(40, "希腊", Pattern.compile("^(30){1}\\-\\d{7,11}"), "30", "GR"),
        HU(41, "匈牙利", Pattern.compile("^(36){1}\\-\\d{7,11}"), "36", "HU"),
        IE(42, "爱尔兰", Pattern.compile("^(353){1}\\-\\d{7,11}"), "353", "IE"),
        IN(43, "印度", Pattern.compile("^(91){1}\\-\\d{7,11}"), "91", "IN"),
        IL(44, "以色列", Pattern.compile("^(972){1}\\-\\d{7,11}"), "972", "IL"),
        JO(45, "约旦", Pattern.compile("^(962){1}\\-\\d{7,11}"), "962", "JO"),
        KG(46, "吉尔吉斯斯坦", Pattern.compile("^(996){1}\\-\\d{7,11}"), "996", "KG"),
        LU(47, "卢森堡", Pattern.compile("^(352){1}\\-\\d{7,11}"), "352", "LU"),
        LT(48, "立陶宛", Pattern.compile("^(370){1}\\-\\d{7,11}"), "370", "LT"),
        MX(49, "墨西哥", Pattern.compile("^(52){1}\\-\\d{7,11}"), "52", "MX"),
        MA(50, "摩洛哥", Pattern.compile("^(212){1}\\-\\d{7,11}"), "212", "MA"),
        MV(51, "马尔代夫", Pattern.compile("^(960){1}\\-\\d{7,11}"), "960", "MV"),
        MN(52, "蒙古", Pattern.compile("^(976){1}\\-\\d{7,11}"), "976", "MN"),
        NO(53, "挪威", Pattern.compile("^(47){1}\\-\\d{7,11}"), "47", "NO"),
        NG(54, "尼日利亚", Pattern.compile("^(234){1}\\-\\d{7,11}"), "234", "NG"),
        PL(55, "波兰", Pattern.compile("^(48){1}\\-\\d{7,11}"), "48", "PL"),
        PA(56, "巴拿马", Pattern.compile("^(507){1}\\-\\d{7,11}"), "507", "PA"),
        PE(57, "秘鲁", Pattern.compile("^(51){1}\\-\\d{7,11}"), "51", "PE"),
        PT(58, "葡萄牙", Pattern.compile("^(351){1}\\-\\d{7,11}"), "351", "PT"),
        QA(59, "卡塔尔", Pattern.compile("^(974){1}\\-\\d{7,11}"), "974", "QA"),
        RO(60, "罗马尼亚", Pattern.compile("^(40){1}\\-\\d{7,11}"), "40", "RO"),
        ZA(61, "南非", Pattern.compile("^(27){1}\\-\\d{7,11}"), "27", "ZA"),
        CH(62, "瑞士", Pattern.compile("^(41){1}\\-\\d{7,11}"), "41", "CH"),
        LK(63, "斯里兰卡", Pattern.compile("^(94){1}\\-\\d{7,11}"), "94", "LK"),
        SC(64, "塞舌尔", Pattern.compile("^(248){1}\\-\\d{7,11}"), "248", "SC"),
        RS(65, "塞尔维亚", Pattern.compile("^(381){1}\\-\\d{7,11}"), "381", "RS"),
        SA(66, "沙特阿拉伯", Pattern.compile("^(966){1}\\-\\d{7,11}"), "966", "SA"),
        ES(67, "西班牙", Pattern.compile("^(34){1}\\-\\d{7,11}"), "34", "ES"),
        TR(68, "土耳其", Pattern.compile("^(90){1}\\-\\d{7,11}"), "90", "TR"),
        TN(69, "突尼斯", Pattern.compile("^(216){1}\\-\\d{7,11}"), "216", "TN"),
        AE(70, "阿联酋", Pattern.compile("^(971){1}\\-\\d{7,11}"), "971", "AE"),
        VE(71, "委内瑞拉", Pattern.compile("^(58){1}\\-\\d{7,11}"), "58", "VE"),
        VG(72, "英属维尔京群岛", Pattern.compile("^(1284){1}\\-\\d{7,11}"), "1284", "VG");

        private RegisterSupportMobileArea(int selIndex ,
                                          String name,
                                          Pattern checkPattern,
                                          String areaCode,
                                          String abbreviation) {
            areaId = selIndex;
            areaName = name;
            this.checkKey = checkPattern;
            this.code = areaCode;
            this.domainAbbreviation = abbreviation;
        }


        // 地区ID
        int areaId;
        // 地区名
        String areaName;
        // 正则表达式
        Pattern checkKey;
        // 地区的电话区号
        String code;
        //域名缩写
        String domainAbbreviation;
        public int getAreaId() {
            return areaId;
        }
        public String getAreaName() {
            return areaName;
        }
        public Pattern getCheckKey() {
            return checkKey;
        }
        public String getCode() {
            return code;
        }
        public String getDomainAbbreviation() {
            return domainAbbreviation;
        }

        public static RegisterSupportMobileArea domainAbbreviationOf(String abbreviation) {
            for(RegisterSupportMobileArea a : values()) {
                if(a.domainAbbreviation.equalsIgnoreCase(abbreviation)) {
                    return a;
                }
            }

            return null;
        }

        public static boolean isValidMobileNumber(String src,String area) {
            return domainAbbreviationOf(area).getCheckKey().matcher(src).matches();
        }

        public static boolean isValidMobileNumber(String src) {
            for(RegisterSupportMobileArea a : values()) {
                if(a.checkKey.matcher(src).matches()) {
                    return true;
                }
            }

            return false;
        }

    }

    /**
     * 判断输入的字符串是否和传入的正则是否匹配。
     *
     * @param   src     待判断的输入字符串
     * @param   reg     正则表达式
     * @return  True    输入的字符串是否和传入的正则匹配
     *          False   输入的字符串是否和传入的正则不匹配
     */
    public static boolean isValidReg(String src, String reg) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(reg);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法用户姓名，长度未限制。
     *
     * @param src 待判断的输入字符串
     * @return True:是合法的用户姓名
     *         False:不是合法的用户姓名
     */
    public static boolean isValidUserName(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_USER_NAME);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法email格式，长度未限制。
     *
     * @param   src     待判断的输入字符串
     * @return  True    是合法的email
     *          False   不是合法的email
     */
    public static boolean isValidEmail(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法post格式，长度为6位。
     *
     * @param   src     待判断的输入字符串
     * @return  True    是合法的post
     *          False   不是合法的post
     */
    public static boolean isValidPost(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(POST);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是国内手机号码格式。
     *
     * @param   src     待判断的输入字符串
     * @return  True    是合法的手机号码
     *          False   不是合法的手机号码
     */
    public static boolean isValidMobile(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_MOBILE);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是国际手机号码格式。
     *
     * @param   src     待判断的输入字符串
     * @return  True    是合法的手机号码
     *          False   不是合法的手机号码
     */
    public static boolean isValidForeignMobile(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        return RegisterSupportMobileArea.isValidMobileNumber(src);
    }

    public static boolean isValidForeignMobile(String src, String area) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        return RegisterSupportMobileArea.domainAbbreviationOf(area).getCheckKey().matcher(src)
            .matches();
    }

    /**
     * 判断输入的字符串是否是合法的固话。
     *
     * @param   src     待判断的输入字符串
     * @return  True    合法
     *          False   不合法
     */
    public static boolean isValidPhone(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_PHONE);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法的支付宝登录号。
     * <p>
     * 支付宝登录号有两种格式。其一是手机号码；其二是Email（长度未限制）
     *
     * @param   src     待判断的输入字符串
     * @return  True    是合法的登录号
     *          False   不是合法的登录号
     */
    public static boolean isValidLogonId(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_LOGON_ID);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是合法的流水号，标准:流水号为16位纯数字。
     *
     * @param src 待判断的输入字符串
     * @return True是合法的流水号
     *         False表示不是合法的流水号
     */
    public static boolean isValidBizNo(String src) {
        // 输入为空则直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(REGEX_VALIDATE_BIZ_NO);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是替身邮。
     *
     * @param   src      待判断的输入字符串
     * @return  True     是替身邮
     *          False    不是替身邮
     */
    public static boolean isSubEmail(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_SUBSTITUTE_EMAIL);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是合法的金额。标准:<br/>
     * <ul>
     * <li>不为空</li>
     * <li>允许3个连续数字用,分隔</li>
     * <li>允许小数点</li>
     * <li>小数位允许超过多为的数字(多余2位将四舍五入)</li>
     * <li>允许正负金额</li>
     * </ul>
     * @param   src     待判断的输入字符串
     * @return  True    表示是合法的金额
     *          False   表示不是合法的金额
     */
    public static boolean isValidMoney(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(REGEX_VALIDATE_MONEY);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是合法的数字。标准:<br/>
     * <ul>
     * <li>不为空</li>
     * <li>不允许小数点</li>
     * </ul>
     * @param   src     待判断的输入字符串
     * @return  True    表示是合法的金额
     *          False   表示不是合法的金额
     */
    public static boolean isValidNum(String src) {
        // 无效的输入，直接返回false
        if (StringUtil.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(NUM);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断用户输入的url是否为合法：必须要以：http[s]开头
     *
     * @param   src     待判断的输入字符串
     * @return  True    表示是合法的金额
     *          False   表示不是合法的金额
     */
    public static boolean isValidUrl(String src) {
        if (StringUtil.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(REGEX_URL);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断用户输入的jsonp函数是否符合要求
     *
     * @param   src     待判断的输入字符串
     * @return  True    表示是合法的jsonp回调函数
     *          False   表示不是合法的jsonp回调函数
     */
    public static boolean isValidJsonpCallBack(String src) {
        if (StringUtil.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(JSONOP_CALLBACK);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断用户输入的字符串是否为字母数字组合，并不超过length制定长度，length为0长度不限制
     *
     * @param   src     待判断的输入字符串
     * @param   length  字符串长度限制，0表示长度不限制
     * @return  True    表示是符合要求
     *          False   表示不是合法要求
     */
    public static boolean isValidString(String src, int length) {
        if (StringUtil.isBlank(src)) {
            return false;
        }
        if (length > 0 && src.length() > length) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_STR_NUM);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断用户输入的url是否为合法：必须要以：http[s]开头
     *
     * @param url
     * @return
     */
    public static boolean isValidUrlForCoupon(String url) {
        if (!StringUtil.isBlank(url)) {
            Pattern p = Pattern.compile(REGX_URL_COUPON);
            Matcher pm = p.matcher(url);
            return pm.matches();
        }
        return false;
    }

}
