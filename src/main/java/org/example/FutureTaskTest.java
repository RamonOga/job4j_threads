package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running");
            }
        };
        Callable<String> coll = new Callable<>() {
            @Override
            public String call() throws Exception {
                String rsl = "Calling";
                System.out.println(rsl);
                return rsl;
            }
        };
        FutureTask<String> tf = new FutureTask(coll);
        FutureTask ft = new FutureTask(run, 2);

        tf.run();
        ft.run();


        tf.get();
        ft.get();
    }
}
