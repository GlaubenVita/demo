package com.example.demo.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, String> map = new HashMap<>();
    //读操作不加锁，写操作加锁
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "，写入开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + ",写入结束");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.writeLock().unlock();
        }

    }

    public void get(String key) {
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "，读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName() + ",读取结束");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache =new MyCache();
        for(int i = 0;i <=10;i++){
            final int tempi = i;
            new Thread(()->{
                myCache.put(tempi+"",tempi+"");
            },String.valueOf(i)).start();
        }
        for(int i = 0;i <=10;i++){
            final int tempi = i;
            new Thread(()->{
                myCache.get(tempi+"");
            },String.valueOf(i)).start();
        }
    }
}
