/*
 * Copyright (c) 2014
 */

package org.gver;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-06-07.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context.start();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();


    }

}
