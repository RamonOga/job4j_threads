package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(4);
        CyclicBarrier barrierWithRun = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in Barrier" + Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(ThreadFabric(barrierWithRun)).start();
        }





    }

    public static Runnable ThreadFabric(CyclicBarrier cb) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long)(Math.random() * 10000L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Start : " + Thread.currentThread().getName());
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("Finish : " + Thread.currentThread().getName());
            }
        };
    }


}
