package ru.job4j.concurrent;

public final class Cache {
    private static Cache cache;

    public static Cache instOf() {
        Object monitor = new Object();
       if (cache == null) {
           synchronized (monitor) {
               if (cache == null) {
                   cache = new Cache();
               }
           }
        }
        return cache;
    }
}