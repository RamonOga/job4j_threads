package ru.job4j.userStorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    Map<Integer, User> users;
    public UserStorage(Map<Integer, User> users) {
        this.users = users;
    }

    @Override
    public synchronized boolean add(User user) {
        return users.put(user.getId(), user) == null;
    }

    @Override
    public boolean update(User user) {
        users.remove(user.getId());
        return add(user);
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int told, int amount) {
        User from = users.get(fromId);
        User to = users.get(told);
        if (from.getAmount() < amount) {
            from.changeAmount(-amount);
            to.changeAmount(amount);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
