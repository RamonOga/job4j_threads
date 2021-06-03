package ru.job4j.pool;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i != 100; i++) {
            threadPool.work(() -> {
                    System.out.println("Task complected.." + Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
    }
}
