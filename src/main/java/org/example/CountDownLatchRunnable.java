package org.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchRunnable implements Runnable {
    CountDownLatch cdl;

    public CountDownLatchRunnable(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i != 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            cdl.countDown();
        }
    }
}
