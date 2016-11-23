/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.springboot.learn.util.StringUtil;

/**
 * socket调用测试
 * @author liuzq
 * @version $Id: SocketTest.java, v 0.1 2016年10月18日 下午4:21:09 liuzq Exp $
 */
public class SocketTest {

    /** 预付卡补位 */
    private static final String zero = "0000000";

    public static void main(String[] args) {
        String info = "432423423423";
        try {
            System.out.println(yfkclient(info));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String yfkclient(String info) throws IOException {
        StringBuffer bf = new StringBuffer();
        String result = null;
        // 1.建立客户端socket连接，指定服务器位置及端口
        Socket socket = null;
        // 2.得到socket读写流
        OutputStream os = null;
        PrintWriter pw = null;
        // 输入流
        InputStream is = null;
        BufferedReader br = null;
        try {
            // 1.建立客户端socket连接，指定服务器位置及端口
            socket = new Socket("192.168.22.233", new Integer("8100"));
            socket.setSoTimeout(60 * 1000);
            // 2.得到socket读写流
            os = socket.getOutputStream();
            pw = new PrintWriter(new OutputStreamWriter(os), true);

            // 输入流
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "gbk"));
            // 3.利用流按照一定的操作，对socket进行读写操作
            // info=info+"\n\r";
            int len = info.getBytes().length + 42;

            String len1 = String.valueOf(len);
            int length = len1.length();
            if (length < 8) {
                len1 = zero.substring(0, zero.length() - length + 1) + len1;
            } else {
                return null;
            }
            pw.write(len1 + "1" + "INTERNET        POSP            1" + info + "FFFFFFFF\n");
            System.out.println("发送ESB【预付卡】报文：" + len1 + "1" + "INTERNET        posp            1"
                               + info + "FFFFFFFF\n");
            pw.flush();
            // 接收服务器的相应

            String reply = null;
            boolean flag = true;
            while (flag) {
                reply = br.readLine();
                if (reply == null || StringUtil.isBlank(reply)) {
                    System.out.println("调用ESB异常，返回信息为空");
                    socket.close();
                    throw new IOException("调用ESB异常，返回信息为空");
                }
                bf.append(reply.trim());
                String string = bf.toString();
                // int startLenth = Integer.valueOf(string.substring(0, 8));
                // String endString = string.substring(8, string.length());
                // byte[] endByte = endString.getBytes("UTF8");
                if (string.endsWith("FFFFFFFF")) {
                    flag = false;
                }
            }

            result = bf.toString().substring(42, bf.toString().length() - 8).trim();
            System.out.println("ESB返回信息【预付卡】：" + result);
        } catch (SocketTimeoutException e) {
            System.err.println("调用ESB异常，异常信息【系统响应超时】");
            throw e;
        } catch (IOException e) {
            System.err.println("调用ESB异常，异常信息【" + e.getMessage() + "】");
            throw e;
        } finally {
            try {
                // 4.关闭资源
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
                if (pw != null) {
                    pw.close();
                }
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ioException) {
                System.err.println("关闭资源异常");
            }
        }
        return result;
    }
}
