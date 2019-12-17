package com.example.demo.JUC;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args){
        //模拟银行五个窗口/一池多线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池一线
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池n线
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try{
            //最多5+3个客户办理业务
            for (int i = 0; i < 8; i++) {
                final int tempi = i;
                threadPool.execute(()->{System.out.println(Thread.currentThread().getName()+"办理业务"+tempi);});
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
