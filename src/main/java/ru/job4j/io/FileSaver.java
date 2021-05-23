package ru.job4j.io;

import java.io.*;

public class FileSaver {
    private final File file;

    public FileSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            out.write(content);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}