package com.example.demo.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();

    Condition c5 = lock.newCondition();
    Condition c10 = lock.newCondition();
    Condition c15 = lock.newCondition();

    public void prent05() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                c5.await();
            }
            //干活
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            //通知
            number=2;
            c10.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void prent10() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                c10.await();
            }
            //干活
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            //通知
            number=3;
            c15.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void prent15() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                c15.await();
            }
            //干活
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            //通知
            number=1;
            c5.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try{
                    shareResource.prent05();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try{
                    shareResource.prent10();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try{
                    shareResource.prent15();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
