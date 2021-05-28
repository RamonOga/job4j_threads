package ru.job4j.prodcons;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int size = 0;

    public int size() {
        return size;
    }

    public synchronized void offer(T value) {
        System.out.println("Offered..");
        queue.offer(value);
        size += 1;
        notifyAll();
    }

    public synchronized T poll() {
        while (queue.peek() == null) {
            try {
                System.out.println("waiting..");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("polling..");
        T rsl = queue.poll();
        size -= 1;
        notifyAll();
        return rsl;
    }
}