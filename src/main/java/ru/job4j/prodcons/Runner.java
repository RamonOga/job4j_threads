package ru.job4j.prodcons;

import java.util.UUID;

public class Runner {
    public static void main(String[] args) {
        SimpleBlockingQueue<UUID> sbq = new SimpleBlockingQueue<>(5);
        Thread th1 = new Thread(() -> {
            while (true) {
                try {
                    sbq.offer(UUID.randomUUID());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );

        Thread th2 = new Thread(() -> {
            while (true) {
                try {
                    sbq.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );

        th1.start();
        th2.start();
    }
}
