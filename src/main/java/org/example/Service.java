package org.example;

import java.util.concurrent.Semaphore;

public class Service {
    private volatile int count = 0;
    private final Semaphore semaphore = new Semaphore(2);

    public void interateCount() throws InterruptedException {
        semaphore.acquire();
        Thread.sleep(1000000);
        semaphore.release();
    }

    public Semaphore getSemaphoreState() {
        return semaphore;
    }

}
