package ru.job4j.prodcons;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue;
    private final int size;

    public SimpleBlockingQueue(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= size) {
            System.out.println("Offer waiting.." + Thread.currentThread());
            wait();
        }
        System.out.println("Offered.."+ Thread.currentThread());
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                System.out.println("Pooling waiting.." + Thread.currentThread());
                wait();
        }
        System.out.println("polling.."+ Thread.currentThread());
        T rsl = queue.poll();
        notifyAll();
        return rsl;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}