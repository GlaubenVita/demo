package com.example.demo.JUC;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NoSafeDemo {
    public static void main(String[] args){
        //Collections：所有java集合的工具类

        Map<Object, Object> maps = new ConcurrentHashMap<>();
        for(int i = 0;i <=3;i++){
            new Thread(()->{
                maps.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,6));
                System.out.println(maps);
            },String.valueOf(i)).start();
        }


        /*Set<Object> sets = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for(int i = 0;i <=3;i++){
            new Thread(()->{
                sets.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(sets);
            },String.valueOf(i)).start();
        }*/


       /* List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>()); //new Vector<>();//new ArrayList<>();
        for(int i = 0;i <=3;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }*/
    }
}
