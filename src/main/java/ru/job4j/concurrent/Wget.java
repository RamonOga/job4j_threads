package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i != 101; i++) {
            Thread.sleep(1000);
            System.out.print("\rLoading : " + i  + "%");
        }
    }
}
