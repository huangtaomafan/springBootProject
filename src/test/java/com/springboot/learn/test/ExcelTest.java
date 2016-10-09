/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.springboot.learn.util.ExcelUtil;

/**
 * 数据迁移脚本生成
 * 
 * @author dell
 * @version $Id: DateTrans.java, v 0.1 2016年4月18日 下午2:05:44 dell Exp $
 */
@SuppressWarnings("unused")
public class ExcelTest {

    public static final int     CODE_D5  = 1679616;                                             // 36^4
    public static final int     CODE_D4  = 46656;                                               // 36^3
    public static final int     CODE_D3  = 1296;                                                // 36^2
    public static final int     CODE_D2  = 36;                                                  // 36^1
    public static final int     CODE_D1  = 1;                                                   // 36^0

    public static final int     CODE_MIN = 0;                                                   // (36^0)-1 代表00000
    public static final int     CODE_MAX = 60466175;                                            // (36^5)-1 代表ZZZZZ

    private final static char[] mChars   = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private final static char[] mChars2  = "0123456789ABCDEF".toCharArray();
    private final static String mHexStr  = "0123456789ABCDEF";

    public static void main(String[] args) {
        //        readExcelToSMKCard();
        //        readExcelToBankCard();
        //        readExcelToBindCard();
        //        readExcelToInvt();
        //        readExcelToCustInfo();
        readExcelToUserRel();
        //        readExcelToUserDetail();
        //        readExcelToUsers();
        //        readExcelToUpdateUserLevel();
        //        readExcelToUpdateUserDetail();
        // updateBankCard();
        //        updateBindCard();
        //         writeExcel();
        //
//        String str = "AbX/jLja8JH0zCxVryaKeW8+WfI=";
//        System.out.println(str2HexStr(str));
//        String str2 = "784D676F434A475637515A4A3051535757347542345371617352413D";
//        System.out.println(hexStr2Str(str2));

        // String loginPwd = "sdwork1218";
        // try {
        // loginPwd = SecurityUtil.hashWithBase64Encoded("SHA1",
        // loginPwd.getBytes());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // System.out.println(loginPwd);
    }

    /**
     * 写入excel
     */
    private static void writeExcel() {
        try {
            // 创建excel文件对象
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("D://新增用户信息.xlsx"));
            // 创建一个张表
            XSSFSheet sheet = wb.getSheetAt(0);
            // 创建行对象
            XSSFRow row = null;
            // 创建表格对象
            XSSFCell cell = null;
            // 循环行
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// 判断是否为空
                    continue;
                }
                cell = row.createCell(0);// 创建单元格
                cell.setCellValue(UUID.randomUUID().toString().replace("-", ""));// 赋值
            }

            FileOutputStream out = new FileOutputStream("D://新增用户信息.xlsx");
            wb.write(out);
            out.close();
            wb.close();
            System.out.println("写入完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToUsers() {
        ExcelUtil excelUtil = new ExcelUtil("D://新增用户信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://users.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String emailFlag = "0";
            String level = "1";
            String idflag = "0";
            for (String[] strs : result) {
                if ("3".equals(strs[25])) {
                    emailFlag = "1";
                } else {
                    emailFlag = "0";
                }
                level = "1";
                if ("2".equals(strs[35])) {
                    level = "2";
                }
                if ("1".equals(strs[26])) {
                    level = "3";
                }
                if ("2".equals(strs[35])) {
                    idflag = "1";
                } else {
                    idflag = "0";
                }
                filew
                    .write("insert into lzq_users values('" + strs[0] + "','" + strs[5] + "','"
                           + str2HexStr(strs[6]) + "','0002','" + strs[5] + "__0002','" + strs[5]
                           + "','1','" + strs[2] + "','" + emailFlag + "','01','" + strs[23] + "','"
                           + idflag + "','" + level + "',NULL,'00',NULL,NULL,systimestamp,'"
                           + new Random().nextInt(100) + "');");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToUserDetail() {
        ExcelUtil excelUtil = new ExcelUtil("D://新增用户信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://userDetailNew.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String sex = "";
            String level = "1";
            String cardFlag = "0";
            for (String[] strs : result) {
                if ("1".equals(strs[4])) {
                    sex = "M";
                } else if ("2".equals(strs[4])) {
                    sex = "F";
                } else {
                    sex = "";
                }
                level = "1";
                cardFlag = "0";
                if ("2".equals(strs[35])) {
                    level = "2";
                    cardFlag = "1";
                }
                if ("1".equals(strs[26])) {
                    level = "3";
                }
                filew.write(
                    "insert into lzq_user_detail (id,user_id,user_system,user_type,name,sex,card_flag,user_level,channel_no,regist_channel,gmt_crt_ts,pat_id) values ('"
                            + strs[0] + "','" + strs[5] + "','0002','0','" + strs[3] + "','" + sex
                            + "','" + cardFlag + "','" + level + "','02','02',systimestamp,'"
                            + new Random().nextInt(100) + "');");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToUserRel() {
        ExcelUtil excelUtil = new ExcelUtil("D://0.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://0.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String level = "1";
            for (String[] strs : result) {
//                level = "1";
//                if ("2".equals(strs[35])) {
//                    level = "2";
//                }
//                if ("1".equals(strs[26])) {
//                    level = "3";
//                }
                filew.write(
                    "insert into person (psnid,psnname,idnumber,mobile) values ('"
                            + strs[0] + "','" + strs[1]
                            + "','" + strs[2] + "','" + strs[3] + "');");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToCustInfo() {
        ExcelUtil excelUtil = new ExcelUtil("D://新增客户信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://10custinfoNew.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String sex = "";
            long currCustId = 400000l;
            String currDate = ("20160621");
            String custId = "";
            for (String[] strs : result) {
                if ("1".equals(strs[3])) {
                    sex = "M";
                } else if ("2".equals(strs[3])) {
                    sex = "F";
                } else {
                    sex = "";
                }
                custId = currDate.concat("000" + currCustId);
                filew.write(
                    "insert into lzq_cust_inf (cust_id,cust_type,name,sex,mobile,email,id_type,id_no,gmt_crt_ts,pat_id) values ('"
                            + custId + "','0','" + strs[2] + "','" + sex + "','" + strs[4] + "','"
                            + strs[1] + "','01','" + strs[22] + "',systimestamp,'"
                            + new Random().nextInt(100) + "');");
                filew.write("\r\n");
                currCustId = currCustId + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToInvt() {
        ExcelUtil excelUtil = new ExcelUtil("D://新增用户信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://12InvtNew.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            int currCustId = 1906900;
            String invt = "";
            for (String[] strs : result) {
                invt = getCodeByInt(currCustId);
                filew.write(
                    "insert into lzq_invt_code (id,user_id,user_system,invt_code,pat_id) values ('"
                            + strs[0] + "','" + strs[5] + "','0002','" + invt + "','"
                            + new Random().nextInt(100) + "');");
                filew.write("\r\n");
                currCustId = currCustId + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToBindCard() {
        ExcelUtil excelUtil = new ExcelUtil("D://银行卡信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://银行卡绑卡信息.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            for (String[] strs : result) {
                filew.write(
                    "insert into lzq_bind_card2 (card_pk,id,user_id,user_system,card_seq,rel_status,pat_id,psnid,cardNo) values ('"
                            + UUID.randomUUID().toString().replace("-", "") + "','" + "" + "','"
                            + "" + "','0002','" + "" + "','0','" + new Random().nextInt(100) + "','"
                            + strs[0] + "','" + strs[2] + "');");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToBankCard() {
        ExcelUtil excelUtil = new ExcelUtil("D://银行卡信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://银行卡信息.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            int currCustId = 800000;
            String currDate = "20160621";
            String custId = "";
            for (String[] strs : result) {
                custId = currDate.concat("00000" + currCustId);

                filew.write(
                    "insert into lzq_bank_card2 (card_seq,card_type,card_no,acct_no,pat_id) values ('"
                            + custId + "','01','" + strs[2] + "','" + strs[0] + "','"
                            + new Random().nextInt(100) + "');");
                filew.write("\r\n");
                currCustId = currCustId + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToSMKCard() {
        ExcelUtil excelUtil = new ExcelUtil("D://市民卡信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://市民卡信息.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            int currCustId = 910000;
            String currDate = "20160621";
            String custId = "";
            String cardType = "";
            for (String[] strs : result) {
                custId = currDate.concat("00000" + currCustId);
                if ("1".equals(strs[3])) {
                    cardType = "02";
                } else if ("2".equals(strs[3])) {
                    cardType = "03";
                } else if ("3".equals(strs[3])) {
                    cardType = "04";
                } else if ("4".equals(strs[3])) {
                    cardType = "05";
                } else if ("7".equals(strs[3])) {
                    cardType = "06";
                }
                filew.write(
                    "insert into lzq_bank_card2 (card_seq,card_type,card_no,acct_no,pat_id) values ('"
                            + custId + "','" + cardType + "','" + strs[2] + "','" + strs[1] + "','"
                            + new Random().nextInt(100) + "');");
                filew.write("\r\n");
                currCustId = currCustId + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToUpdateUserLevel() {
        ExcelUtil excelUtil = new ExcelUtil("D://custinfo.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://updateUsers.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String level = "1";
            for (String[] strs : result) {
                if ("1".equals(strs[25])) {
                    level = "3";
                } else {
                    level = "2";
                }
                filew.write("update lzq_users set user_level = '" + level + "',id_no='" + strs[22]
                            + "' where user_id='" + strs[4] + "';");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void readExcelToUpdateUserDetail() {
        ExcelUtil excelUtil = new ExcelUtil("D://新增客户信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://11updateUserDetailNew.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            //            String level = "1";
            long currCustId = 400000l;
            String currDate = ("20160621");
            String custId = "";
            for (String[] strs : result) {
                //                if ("1".equals(strs[25])) {
                //                    level = "3";
                //                } else {
                //                    level = "2";
                //                }
                custId = currDate.concat("000" + currCustId);
                //                filew.write("update lzq_user_detail set user_level = '" + level + "',cust_id='"
                //                            + custId + "',name='" + strs[2] + "',card_flag = '1' where user_id='"
                //                            + strs[4] + "';");
                filew.write("update lzq_user_detail set cust_id='" + custId + "' where user_id='"
                            + strs[4] + "';");
                filew.write("\r\n");
                currCustId = currCustId + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void updateBankCard() {
        ExcelUtil excelUtil = new ExcelUtil("D://updatebank.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://updateBankCard.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String cardType = "";
            for (String[] strs : result) {
                if ("1".equals(strs[3])) {
                    cardType = "02";
                } else if ("2".equals(strs[3])) {
                    cardType = "03";
                } else if ("3".equals(strs[3])) {
                    cardType = "04";
                } else if ("4".equals(strs[3])) {
                    cardType = "05";
                } else if ("7".equals(strs[3])) {
                    cardType = "06";
                }

                filew.write("update lzq_bank_card set card_type = '" + cardType
                            + "' where card_seq='" + strs[13] + "';");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取excel,生成sql脚本
     */
    private static void updateBindCard() {
        ExcelUtil excelUtil = new ExcelUtil("D://重复卡信息.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://updateBindCard.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            for (String[] strs : result) {
                filew.write("update lzq_bind_card2 set card_seq = '" + strs[0]
                            + "' where card_seq='" + strs[1] + "';");
                filew.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                filew.close();
                System.out.println("DONE!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 整数值转换为5位邀请码 iCode = A5*36^4+A4*36^3+A3*36^2+A2*36^1+A1*36^0 iCode =>
     * A5A4A3A2A1
     * 
     * @param iCode
     *            整数值
     * @return
     */
    public static String getCodeByInt(int iCode) {
        if (iCode < CODE_MIN || iCode > CODE_MAX) {
            return null;
        }
        String strCode = null;
        int A5 = iCode / CODE_D5;
        int B5 = iCode % CODE_D5;
        int A4 = B5 / CODE_D4;
        int B4 = B5 % CODE_D4;
        int A3 = B4 / CODE_D3;
        int B3 = B4 % CODE_D3;
        int A2 = B3 / CODE_D2;
        int B2 = B3 % CODE_D2;
        int A1 = B2;// B2除以CODE_D1(1)
        StringBuilder sb = new StringBuilder();
        sb.append(mChars[A5]);
        sb.append(mChars[A4]);
        sb.append(mChars[A3]);
        sb.append(mChars[A2]);
        sb.append(mChars[A1]);
        strCode = sb.toString();
        return strCode;
    }

    /**
     * 字符串转换成十六进制字符串
     * 
     * @param str
     *            String 待转换的ASCII字符串
     * @return String
     */
    public static String str2HexStr(String str) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; i++) {
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
            sb.append(mChars[bs[i] & 0x0F]);
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制字符串转换成 ASCII字符串
     * 
     * @param str
     *            String Byte字符串
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {

        hexStr = hexStr.toString().trim().replace(" ", "").toUpperCase(Locale.US);
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int iTmp = 0x00;

        for (int i = 0; i < bytes.length; i++) {
            iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;
            iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (iTmp & 0xFF);
        }

        return new String(bytes);
    }
}
