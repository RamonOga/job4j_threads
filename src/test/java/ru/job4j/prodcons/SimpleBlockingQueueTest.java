package ru.job4j.prodcons;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;


public class SimpleBlockingQueueTest {

    @Test
    public void SimpleBlockingQueueTesting() throws InterruptedException {
        SimpleBlockingQueue<UUID> sbq = new SimpleBlockingQueue<>();
        UUID uuid = UUID.randomUUID();
        Thread one = new Thread(() -> sbq.offer(uuid));
        Thread two = new Thread(sbq::poll);

        one.start();
        one.join();
        Assert.assertEquals(1, sbq.size());

        two.start();
        two.join();
        Assert.assertEquals(0, sbq.size());
    }

}