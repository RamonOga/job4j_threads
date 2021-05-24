package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList() {
        list = new ArrayList<>();
    }

    public SingleLockList(List<T> list) {
        this.list = copy(list);
        //this.list = (List) list.clone(); Этот код не работал.
    }

    public synchronized void add(T value) {
        if (value == null) {
            throw  new IllegalArgumentException("Value cannot be null");
        }
        list.add(value);
    }

    public synchronized T get(int index) {
        if (index < 0) {
            throw  new IllegalArgumentException("Index cannot be less than 0");
        }
        return list.get(index);
    }

    public synchronized List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}