package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));

        first.start();
        second.start();
        while (true) {
            if (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
                System.out.println("Please wait, work in progress");
            } else {
                System.out.println("Work completed ");
                break;
            }
       }
    }
}