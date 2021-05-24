package ru.job4j.userStorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    private final Map<Integer, User> users;
    public UserStorage(Map<Integer, User> users) {
        this.users = users;
    }

    @Override
    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int told, int amount) {
        User from = users.get(fromId);
        User to = users.get(told);

        if (from != null && to != null && from.getAmount() < amount) {
            from.changeAmount(-amount);
            to.changeAmount(amount);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, User> mu = new HashMap<>();
        mu.put(1, new User(1, 100));
        mu.put(2, new User(2, 200));
        System.out.println(mu);
        System.out.println(mu.replace(1, new User(3, 300)));
        System.out.println(mu.replace(4, new User(3, 300)));
        System.out.println(mu);
    }
}
