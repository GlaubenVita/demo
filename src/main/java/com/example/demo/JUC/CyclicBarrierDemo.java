package com.example.demo.JUC;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args){
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println("七颗龙珠，召唤神龙");});
        for(int i = 0;i <=6;i++){
            final int tempi=i;
            new Thread(()->{
                try{
                    System.out.println(Thread.currentThread().getName()+"收集到第:"+tempi+"颗龙珠");
                    cyclicBarrier.await();
                }catch(Exception e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
