package ru.job4j.prodcons;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.Callable;


public class SimpleBlockingQueueTest {

    @Test
    public void SimpleBlockingQueueTesting() throws InterruptedException {
        SimpleBlockingQueue<UUID> sbq = new SimpleBlockingQueue<>(5);
        UUID uuid = UUID.randomUUID();
        Thread one = new Thread(() -> {
            try {
                sbq.offer(uuid);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                sbq.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        one.start();
        one.join();

        two.start();
        two.join();
    }

}