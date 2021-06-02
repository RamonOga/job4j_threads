package ru.job4j.pool;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i != 10000; i++) {
            Thread.sleep(50);
            threadPool.work(() -> {
                while (true) {
                    System.out.println("Task complected.." + Thread.currentThread());
                }
            });
        }
        threadPool.shutdown();
    }
}
