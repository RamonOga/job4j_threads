package ru.job4j.pool;

import ru.job4j.prodcons.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() throws InterruptedException {
        threads = new LinkedList<>();
        tasks = new SimpleBlockingQueue<>(10);
        initThreads();
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private void initThreads() throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread.sleep(500);
            Thread thread = new Thread(new Work(tasks));
            threads.add(thread);
            thread.start();
        }
    }
}