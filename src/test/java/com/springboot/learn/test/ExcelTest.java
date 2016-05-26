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

    public static void main(String[] args) {
        // readExcelToSMKCard();
        // readExcelToBankCard();
        // readExcelToBindCard();
        // readExcelToInvt();
        // readExcelToCustInfo();
        // readExcelToUserRel();
        // readExcelToUserDetail();
        // readExcelToUsers();
        readExcelToUpdateUserLevel();
        // readExcelToUpdateUserDetail();
        // updateBankCard();
        // writeExcel();

        // String str = "MW70pD+QiHTtHhfOnTa6mC4ZE5M=";
        // System.out.println(StringUtil.str2HexStr(str));
        // String str2 =
        // "4D57373070442B51694854744868664F6E5461366D43345A45354D3D";
        // System.out.println(StringUtil.hexStr2Str(str2));

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
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("D://L3.xlsx"));
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

            FileOutputStream out = new FileOutputStream("D://L3.xlsx");
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
        ExcelUtil excelUtil = new ExcelUtil("D://L3.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://L3.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String emailFlag = "0";
            for (String[] strs : result) {
                if ("3".equals(strs[25])) {
                    emailFlag = "1";
                } else {
                    emailFlag = "0";
                }
                filew.write("insert into lzq_users values('" + strs[0] + "','" + strs[5] + "','"
                            + (strs[6]) + "','0002','" + strs[5] + "__0002','" + strs[5] + "','1','"
                            + strs[2] + "','" + emailFlag + "','01','" + strs[23]
                            + "','1','3',NULL,'00',NULL,NULL,systimestamp,'"
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
        ExcelUtil excelUtil = new ExcelUtil("D://L1.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://L1Detail.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String sex = "";
            for (String[] strs : result) {
                if ("1".equals(strs[4])) {
                    sex = "M";
                } else if ("2".equals(strs[4])) {
                    sex = "F";
                } else {
                    sex = "";
                }
                filew.write(
                    "insert into lzq_user_detail (id,user_id,user_system,user_type,name,sex,card_flag,user_level,channel_no,regist_channel,gmt_crt_ts,pat_id) values ('"
                            + strs[0] + "','" + strs[5] + "','0002','0','" + strs[3] + "','" + sex
                            + "','1','3','02','02',systimestamp,'" + new Random().nextInt(100)
                            + "');");
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
        ExcelUtil excelUtil = new ExcelUtil("D://userRel.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://TBL_USER_REL.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String level = "1";
            for (String[] strs : result) {
                if ("1".equals(strs[1])) {
                    level = "3";
                } else {
                    level = "2";
                }
                filew.write(
                    "insert into lzq_user_rel (rel_pk,id,user_id,user_system,rel_type,rel_user,rel_user_lev,rel_user_status,chanel_no,bnd_ts,rel_status,pat_id) values ('"
                            + UUID.randomUUID().toString().replace("-", "") + "','" + strs[2]
                            + "','" + strs[3] + "','0001','04','" + strs[0] + "','" + level
                            + "','00','02',systimestamp,'0','" + new Random().nextInt(100) + "');");
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
        ExcelUtil excelUtil = new ExcelUtil("D://cust.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://cust.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String sex = "";
            long currCustId = 200000l;
            String currDate = ("");
            String custId = "";
            for (String[] strs : result) {
                if ("1".equals(strs[2])) {
                    sex = "M";
                } else if ("2".equals(strs[2])) {
                    sex = "F";
                } else {
                    sex = "";
                }
                custId = currDate.concat("000" + currCustId);
                filew.write(
                    "insert into lzq_cust_inf (cust_id,cust_type,name,sex,mobile,email,id_type,id_no,gmt_crt_ts,pat_id) values ('"
                            + custId + "','0','" + strs[1] + "','" + sex + "','" + strs[3] + "','"
                            + strs[4] + "','01','" + strs[5] + "',systimestamp,'"
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
        ExcelUtil excelUtil = new ExcelUtil("D://Invt.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://Invt.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            int currCustId = 1759616;
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
        ExcelUtil excelUtil = new ExcelUtil("D://bankbind.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://bindCard.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            for (String[] strs : result) {
                filew.write(
                    "insert into lzq_bind_card (card_pk,id,user_id,user_system,card_seq,rel_status,pat_id) values ('"
                            + UUID.randomUUID().toString().replace("-", "") + "','" + strs[1]
                            + "','" + strs[2] + "','0002','" + strs[31] + "','0','"
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
    private static void readExcelToBankCard() {
        ExcelUtil excelUtil = new ExcelUtil("D://smkbank.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://bankCard2.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            int currCustId = 700000;
            String currDate = ("");
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

                filew
                    .write("insert into lzq_bank_card (card_seq,card_type,card_no,pat_id) values ('"
                           + custId + "','" + cardType + "','" + strs[2] + "','"
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
        ExcelUtil excelUtil = new ExcelUtil("D://SMKCard.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://SMKCard.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            int currCustId = 110000;
            String currDate = ("");
            String custId = "";
            for (String[] strs : result) {
                custId = currDate.concat("00000" + currCustId);
                filew.write(
                    "insert into lzq_bank_card (card_seq,card_type,card_no,acct_no,pat_id) values ('"
                            + custId + "','02','" + strs[3] + "','" + strs[2] + "','"
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
        ExcelUtil excelUtil = new ExcelUtil("D://users.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://updateUsers.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            String level = "1";
            for (String[] strs : result) {
                if ("1".equals(strs[1])) {
                    level = "3";
                } else {
                    level = "2";
                }
                filew.write("update lzq_users set user_level = '" + level + "',id_no='" + strs[4]
                            + "' where id='" + strs[2] + "';");
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
        ExcelUtil excelUtil = new ExcelUtil("D://userdetail.xlsx");
        List<String[]> result = excelUtil.getAllData(0);
        File file = new File("D://updateUserDetail.sql");
        FileWriter filew = null;
        try {
            filew = new FileWriter(file);
            for (String[] strs : result) {
                filew
                    .write("update lzq_user_detail set user_level = '" + strs[2] + "',cust_id='"
                           + strs[0] + "',name='" + strs[3] + "' where user_id='" + strs[1] + "';");
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

}
