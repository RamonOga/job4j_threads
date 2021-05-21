package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i != 101; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.print("\rLoading : " + i  + "%");
                    }});
        first.start();
    }
}
