package com.example.demo.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();//当前锁的状态,可以理解为当前锁的钥匙，可以有多个

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();//this.wait();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            //通知
            condition.signalAll();//this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number == 0) {
                condition.await();//this.wait();
            }
            //干活
            --number;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            //通知
            condition.signalAll();//this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //-------------------------------------
    /*public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0) {
            this.wait();
        }
        //干活
        ++number;
        System.out.println(Thread.currentThread().getName() + ":" + number);
        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //判断
        while (number != 1) {
            this.wait();
        }
        //干活
        --number;
        System.out.println(Thread.currentThread().getName() + ":" + number);
        //通知
        this.notifyAll();
    }*/


}

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }


}
