package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCount {
    private volatile int count;
    private ReadWriteLock rwLock;
    private Lock readLock;
    private Lock writeLock;

    public ReadWriteLockCount() {
        this.rwLock  = new ReentrantReadWriteLock();
        readLock = rwLock.readLock();
        writeLock = rwLock.writeLock();
        this.count = 0;
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public void iterCount() {
        writeLock.lock();
        try {
            count += 1;
        } finally {
            writeLock.unlock();
        }

    }
}
