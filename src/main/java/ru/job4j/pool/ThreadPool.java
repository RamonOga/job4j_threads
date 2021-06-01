package ru.job4j.pool;

import ru.job4j.prodcons.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
        for (Thread thread : threads) {
            if (thread.getState() == Thread.State.WAITING) {
                thread.notify();
            }
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private void initThreads() throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(tasks.poll());
            threads.add(thread);
            thread.start();
        }
    }
}