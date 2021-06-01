package ru.job4j.cuncurrentmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base tmp =  memory.computeIfPresent(model.getId(), (key, value) -> {
            Base cacheBase = memory.get(model.getId());
            if (value.getVersion() != cacheBase.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base newBase = new Base(key, value.getVersion() + 1);
            return memory.put(key, newBase);
        });
        return tmp != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(Base base) {
        return memory.get(base.getId());
    }
}