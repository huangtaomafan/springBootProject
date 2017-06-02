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

import com.springboot.learn.util.EncryptUtil;
import com.springboot.learn.util.ExcelUtil;
import com.springboot.learn.util.StringUtil;

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
        //        readExcelToUserRel();
        //        readExcelToUserDetail();
        readExcelToUsers();
        //        readExcelToUpdateUserLevel();
        //        readExcelToUpdateUserDetail();
        // updateBankCard();
        //        updateBindCard();
        //        writeExcel();
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
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("D://users01010411.xlsx"));
            // 创建一个张表
            XSSFSheet sheet = wb.getSheetAt(0);
            // 创建行对象
            XSSFRow row = null;
            // 创建表格对象
            XSSFCell cell = null;
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// 判断是否为空
                    continue;
                }
                System.out.println(i);
                String mobile = EncryptUtil.decrypt(row.getCell(1).getStringCellValue());
                cell = row.createCell(2);// 创建单元格
                cell.setCellValue(mobile);// 赋值
            }
            FileOutputStream out = new FileOutputStream("D://users01010411.xlsx");
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
        ExcelUtil excelUtil = new ExcelUtil("D://L1.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://L1.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String user_system = "0002";
            String mobile_verify_flag = "1";
            String id_type = "01";
            String id_verify_flag = "1";
            String level = "2";
            String user_status = "00";
            String mob_flag = "1";
            long currCustId = 999000l;
            String currDate = ("20170529");
            String custId = "";
            String cardSeq = "";
            int invtId = 643751;
            String invt = "";
            for (String[] strs : result) {
                String id = UUID.randomUUID().toString().replace("-", "");
                String password = "hfi123456";
                password = EncryptUtil.encodePwd(password);
                String mobile = EncryptUtil.encrypt(StringUtil.trim(strs[1]));
                String id_no = EncryptUtil
                    .encrypt(StringUtil.trim(StringUtil.toUpperCase(StringUtil.trim(strs[2]))));
                String name = StringUtil.trim(strs[0]);
                custId = currDate.concat("000" + currCustId);
                cardSeq = currDate.concat("00000" + currCustId);
                invt = getCodeByInt(invtId);
                //                filew.write(
                //                    "insert into tbl_users (id,user_id,password,user_system,mobile,mobile_verify_flag,id_type,id_no,id_verify_flag,user_level,user_status,gmt_crt_ts,pat_id,mob_flag) values('"
                //                            + id + "','" + id + "','" + password + "','" + user_system + "','"
                //                            + mobile + "','" + mobile_verify_flag + "','" + id_type + "','" + id_no
                //                            + "','" + id_verify_flag + "','" + level + "','" + user_status
                //                            + "',systimestamp,'" + new Random().nextInt(100) + "','" + mob_flag
                //                            + "');");
                //                filew.write("\r\n");
                //
                //                filew.write(
                //                    "insert into tbl_user_detail (id,user_id,user_system,user_type,cust_id,name,user_level,channel_no,regist_channel,gmt_crt_ts,pat_id) values ('"
                //                            + id + "','" + id + "','0002','0','" + custId + "','" + name + "','"
                //                            + level + "','02','02',systimestamp,'" + new Random().nextInt(100)
                //                            + "');");
                //                filew.write("\r\n");

                filew.write("update tbl_users set id_no = '" + id_no + "' where user_id='" + strs[4]
                            + "';");
                filew.write("\r\n");

                filew.write("update tbl_user_detail set name = '" + name + "' where user_id='"
                            + strs[4] + "';");
                filew.write("\r\n");

                filew.write(
                    "insert into tbl_cust_inf (cust_id,cust_type,name,mobile,id_type,id_no,gmt_crt_ts,pat_id) values ('"
                            + custId + "','0','" + name + "','" + mobile + "','01','" + id_no
                            + "',systimestamp,'" + new Random().nextInt(100) + "');");
                filew.write("\r\n");

                //                filew.write(
                //                    "insert into tbl_invt_code (id,user_id,user_system,invt_code,pat_id) values ('"
                //                            + id + "','" + id + "','0002','" + invt + "','"
                //                            + new Random().nextInt(100) + "');");
                //                filew.write("\r\n");

                filew.write(
                    "insert into tbl_bank_card (card_seq,card_type,card_no,creat_ts,lst_mdy_ts,pat_id) values ('"
                            + cardSeq + "','02','" + strs[3] + "',systimestamp,systimestamp,'"
                            + new Random().nextInt(100) + "');");
                filew.write("\r\n");

                filew.write(
                    "insert into tbl_bind_card (card_pk,id,user_id,user_system,card_seq,creat_ts,lst_mdy_ts,rel_status,pat_id,bind_flag) values ('"
                            + UUID.randomUUID().toString().replace("-", "") + "','" + id + "','"
                            + id + "','0002','" + cardSeq + "',systimestamp,systimestamp,'0','"
                            + new Random().nextInt(100) + "','02');");
                filew.write("\r\n");

                filew.write("---------");
                filew.write("\r\n");

                currCustId = currCustId + 1;
                invtId = invtId + 1;
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
        ExcelUtil excelUtil = new ExcelUtil("D://users1101_0411.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://mobile.csv");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String mobile = "";
            for (String[] strs : result) {
                mobile = EncryptUtil.decrypt(strs[0]);
                filew.write(mobile);
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
                filew.write("insert into person (psnid,psnname,idnumber,mobile) values ('" + strs[0]
                            + "','" + strs[1] + "','" + strs[2] + "','" + strs[3] + "');");
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
        ExcelUtil excelUtil = new ExcelUtil("D://bindflag.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://updateBindFlag1009.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            for (String[] strs : result) {
                filew.write(
                    "update tbl_bind_card set bind_flag = '03' where user_id='" + strs[2] + "';");
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
