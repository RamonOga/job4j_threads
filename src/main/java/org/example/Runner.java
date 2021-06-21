package org.example;

import ru.job4j.pool.ThreadPool;

import java.util.concurrent.*;

public class Runner {
    public static void main(String[] args) {
        while (true) {
            long l = (long) (Math.random() * 10000L);
            System.out.println(l);
        }
    }



}
