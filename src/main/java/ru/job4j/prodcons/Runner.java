package ru.job4j.prodcons;

import java.util.UUID;

public class Runner {
    public static void main(String[] args) {
        SimpleBlockingQueue<UUID> sbq = new SimpleBlockingQueue<>();
        Thread th1 = new Thread(() -> {
            while (true) {
                sbq.offer(UUID.randomUUID());
            }
        }
        );

        Thread th2 = new Thread(() -> {
            while (true) {
                sbq.poll();
            }
        }
        );

        th1.start();
        th2.start();
    }
}
