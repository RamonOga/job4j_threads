package ru.job4j;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized(this) {
            count++;
            notifyAll();
        }
    }

    public void await() {
        synchronized (this) {
            while(count < total) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Runner {
    public static void main(String[] args) {
        CountBarrier counter = new CountBarrier(10);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    counter.await();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    counter.count();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}