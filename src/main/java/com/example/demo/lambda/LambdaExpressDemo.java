package com.example.demo.lambda;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;

@FunctionalInterface
interface LambdaTest{
    //public void  sayHello();

    static int div(int x,int y){
        return x/y;
    };

    public int add(int x,int y);
    //public int sum(int x,int y);
}

public class LambdaExpressDemo {
    public static void main(String[] args){
//        LambdaTest l1 = new LambdaTest() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello");
//            }
//        };
//        l1.sayHello();

//        LambdaTest l2 = ()->{System.out.println("hello2");};
//        l2.sayHello();
        LambdaTest t3 =(int x,int y)->{
            System.out.println("come into methods");
            return x+y;
        };
        System.out.println(t3.add(1,2));

        System.out.println(LambdaTest.div(10,2));


        //只有先标注@FunctionalInterface才能使用lambda表达式

    }

}
