/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  2个线程，其中1个线程每次对 j增加 1 ，另外1个线程对j每次减少1
 * @author liuzq
 * @version $Id: ThreadTest.java, v 0.1 2016年8月15日 上午10:21:53 liuzq Exp $
 */
public class ThreadTest {
    private int  j;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        for (int i = 0; i < 2; i++) {
            new Thread(tt.new Adder()).start();
            new Thread(tt.new Subtractor()).start();
        }
    }

    private class Subtractor implements Runnable {
        @Override
        public void run() {
            while (true) {
                /*synchronized (ThreadTest.this) {
                System.out.println("j--="+ j--);
                //这里抛异常了，锁能释放吗？
                }*/
                lock.lock();
                try {
                    System.out.println("j--=" + j--);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class Adder implements Runnable {
        @Override
        public void run() {
            while (true) {
                /*synchronized (ThreadTest.this) {
                System.out.println("j++="+ j++);
                }*/
                lock.lock();
                try {
                    System.out.println("j++=" + j++);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
