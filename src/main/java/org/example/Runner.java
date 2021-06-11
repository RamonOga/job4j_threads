package org.example;

import ru.job4j.pool.ThreadPool;

import java.util.concurrent.*;

public class Runner {
    private static ReadWriteLockCount count = new ReadWriteLockCount();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(4);
        CountDownLatchRunnable cdlr = new CountDownLatchRunnable(cdl);

        System.out.println("ebat' koltit");
        Thread th1 = new Thread(cdlr);
        th1.start();
        cdl.await();




        System.out.println("doebali dikoltili");
    }
}
