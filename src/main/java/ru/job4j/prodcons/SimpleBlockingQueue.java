package ru.job4j.prodcons;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        queue.offer(value);
        System.out.println("Offered..");
        notifyAll();
    }

    public synchronized T poll() {
        while (queue.poll() == null) {
            try {
                System.out.println("waiting..");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("polling");
        T rsl = queue.poll();
        notifyAll();
        return rsl;
    }
}