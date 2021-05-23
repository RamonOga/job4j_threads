package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class FileLoader {
    private final File file;

    public FileLoader(File file) {
        this.file = file;
    }

    public String getContent() {
        return getContentByPredicate((a) -> true);
    }

    public String getContentWithoutUnicode() {
        return getContentByPredicate((a) -> a < 0x80);
    }

    public String getContentByPredicate(Predicate<Integer> predicate) {
        StringBuilder text = new StringBuilder();
        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if(predicate.test(data)) {
                    text.append((char) data);
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return text.toString();
    }
}