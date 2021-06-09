package org.example;

import ru.job4j.pool.ThreadPool;

import java.util.concurrent.*;

public class Runner {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 15;
            }
        });
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
