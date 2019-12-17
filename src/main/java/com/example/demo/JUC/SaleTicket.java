package com.example.demo.JUC;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args){
        Ticket ticket = new Ticket();

//        精简写法,λ表达式
       /* new Thread(()->{for (int i=0;i<33;i++) ticket.sale();}, "A").start();
        new Thread(()->{for (int i=0;i<33;i++) ticket.sale();}, "B").start();
        new Thread(()->{for (int i=0;i<33;i++) ticket.sale();}, "C").start();*/

       //线程池
        ExecutorService threadPool= Executors.newFixedThreadPool(3);
        try{
            for (int i = 0; i < 30; i++) {
                threadPool.execute(()->{
                    ticket.sale();
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }

//        传统写法
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0;i<33;i++){
//                    ticket.sale();
//                }
//            }
//        },"C").start();
    }
}

class Ticket{
    private int number=30;

    //可复用锁
    private Lock lock = new ReentrantLock();

    public  void sale(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"张,还剩"+number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}