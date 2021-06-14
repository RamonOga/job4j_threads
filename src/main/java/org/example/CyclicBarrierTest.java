package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);
        CyclicBarrier barrierWithRun = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in Barrier" + Thread.currentThread().getName());
            }
        });

        new Thread(ThreadFabric(barrier)).start();
        new Thread(ThreadFabric(barrier)).start();
        new Thread(ThreadFabric(barrier)).start();




    }

    public static Runnable ThreadFabric(CyclicBarrier cb) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Start : " + Thread.currentThread().getName());
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finish : " + Thread.currentThread().getName());
            }
        };
    }


}
