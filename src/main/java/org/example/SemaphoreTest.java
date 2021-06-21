package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i != 4; i++) {
            Thread thread = new Thread(new TestWorker(semaphore));
            threads.add(thread);
            thread.start();
        }

        while (threads.get(0).getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Thread thread : threads) {
                System.out.println(thread.getName() + " : " + thread.getState());
            }
        }
    }
}

class TestWorker implements Runnable {

    Semaphore semaphore;

    public TestWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    private void something() throws InterruptedException {
        semaphore.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + " acquire");
            Thread.sleep(2000);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public void run() {
        try {
            something();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
