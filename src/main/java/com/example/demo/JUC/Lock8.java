package com.example.demo.JUC;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws InterruptedException {
        //枚举类。可以定义单位
        TimeUnit.SECONDS.sleep(2);
        System.out.println(".........sendEmail");
    }
    public synchronized void sendSMS(){
        System.out.println(".........sendSMS");
    }
    public void hello(){
        System.out.println("hello........");
    }
}

public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(()->{
            try{
                phone.sendEmail();
            }catch(Exception e){
                e.printStackTrace();
            }
        },"A").start();
        //主线程睡
        Thread.sleep(100);

        new Thread(()->{
            try{
                //phone.sendSMS();
                //phone.hello();
                phone1.sendSMS();
            }catch(Exception e){
                e.printStackTrace();
            }
        },"B").start();
    }

}
