package org.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchRunnable implements Runnable {

    CountDownLatch cdl;

    public CountDownLatchRunnable(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {

            try {
                Thread.sleep((long) (Math.random() * 10000L));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        try {
            System.out.println(Thread.currentThread().getName() + " awiat");
            cdl.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " working!");
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(9);
        Thread awaitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("await thread start");
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("await thread finish");

            }
        });
        awaitThread.start();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new CountDownLatchRunnable(cdl));
            thread.start();
        }


    }
}
