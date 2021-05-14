package ru.job4j.concurrent;

import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread thread2 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

            System.out.println(Thread.currentThread().getName());
            thread1.start();
            thread2.start();
    }
}
