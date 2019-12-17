package com.example.demo.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("s"));
        System.out.println(blockingQueue.add("d"));
//        System.out.println(blockingQueue.add("f"));
        /*for (int i = 0; i < 3; i++) {
            blockingQueue.remove();
        }*/

        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue2.offer("q"));
        System.out.println(blockingQueue2.offer("w"));
        System.out.println(blockingQueue2.offer("e"));
        System.out.println(blockingQueue2.offer("e",3L, TimeUnit.SECONDS));
        for (int i = 0; i < 4; i++) {
            System.out.println(blockingQueue2.poll());
        }

        blockingQueue3.put("z");
        blockingQueue3.put("x");
        blockingQueue3.put("c");
        for (int i = 0; i < 4; i++) {
            System.out.println(blockingQueue3.take());
        }
    }
}
