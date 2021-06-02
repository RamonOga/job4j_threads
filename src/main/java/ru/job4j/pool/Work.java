package ru.job4j.pool;

import ru.job4j.prodcons.SimpleBlockingQueue;

public class Work implements Runnable{

    SimpleBlockingQueue<Runnable> tasks;

    public Work(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        try {
            tasks.poll()
                    .run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
