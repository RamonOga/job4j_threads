package org.example.blockq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
    private final Condition condition;
    private final Lock lock;
    private final Queue<T> queue;
    private final int size;

    public BlockingQueue(int size) {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        queue = new LinkedList<>();
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= size) {
                System.out.println("Offer waiting.." + Thread.currentThread().getName());
                condition.await();
            }
            System.out.println("Offered.."+ Thread.currentThread().getName());
            queue.offer(value);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public T poll () throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Pooling waiting.." + Thread.currentThread().getName());
                condition.await();
            }
            System.out.println("polling.."+ Thread.currentThread().getName());
            condition.signal();
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
