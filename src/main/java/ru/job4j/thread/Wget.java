package ru.job4j.thread;

import jdk.jshell.EvalException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream out = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            long start = System.currentTimeMillis();
            int bytesRead = in.read(dataBuffer, 0, 1024);
            long finish = System.currentTimeMillis();
            sleep(start, finish);
            while (bytesRead != -1) {
                out.write(dataBuffer, 0, bytesRead);
                start = System.currentTimeMillis();
                bytesRead = in.read(dataBuffer, 0, 1024);
                finish = System.currentTimeMillis();
                sleep(start, finish);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate (String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    private void sleep(long start, long finish) {
        long downloadTime = finish - start;
        if (downloadTime < speed) {
            try {
                Thread.sleep(speed - downloadTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();

    }

}