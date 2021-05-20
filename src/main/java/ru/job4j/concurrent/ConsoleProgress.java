package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] chars = new char[]{'\\', '|', '/', '-'};
        int count = 0;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\rLoading..." + chars[count]);
            count++;
            if (count == chars.length) {
                count = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();

            Thread.sleep(1); /* симулируем выполнение параллельной задачи в течение 1 секунды. */

        progress.interrupt();
    }
}


