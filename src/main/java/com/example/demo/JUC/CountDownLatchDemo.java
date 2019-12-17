package com.example.demo.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开教室");
                //计数器
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //计数器为零时放行，否则堵塞
        countDownLatch.await();
        System.out.println("最后一个人锁门");

    }
}
