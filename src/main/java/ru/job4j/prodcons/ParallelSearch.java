package ru.job4j.prodcons;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);


        final Thread consumer = new Thread(
                () -> {
                    boolean run = true;
                    while (run) {
                        try {
                            queue.poll();

                        } catch (InterruptedException e) {
                            run = false;
                        }
                    }
                }
        );
        consumer.start();
        final Thread produce = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {

                        try {
                            queue.offer(index);
                            Thread.sleep(500);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        produce.start();
        try {
            produce.join();
            consumer.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}