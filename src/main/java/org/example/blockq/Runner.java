package org.example.blockq;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new BlockingQueue<>(5);
        List<Thread> threads = new ArrayList<>();

        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    int time = (int) (Math.random() * 5001);
                    Thread.sleep(time);
                    q.offer(time);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threads.add(producer);

        Thread consumer = new Thread(() -> {
            try {
               while (true) {
                   int time = (int) (Math.random() * 10002);
                   Thread.sleep(time);
                   q.poll();
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threads.add(consumer);
        while (true) {
            Thread.sleep(1000);
            for (Thread th : threads) {
                if (th.getState() == Thread.State.NEW) {
                    th.start();
                }
              //  System.out.println(th.getState());
            }
        }
    }
}
