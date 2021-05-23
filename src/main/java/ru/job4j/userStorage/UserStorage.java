package ru.job4j.userStorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    List<User> users;
    public UserStorage(List<User> users) {
        this.users = users;
    }

    @Override
    public synchronized boolean add(User user) {
        return users.add(user);
    }

    @Override
    public synchronized boolean update(User user) {
        return users.stream().anyMatch(a -> a.equals(user));
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int told, int amount) {
        User from = getUserById(fromId);
        User to = getUserById(told);
        if (from.getAmount() < amount) {
            from.changeAmount(-amount);
            to.changeAmount(amount);
            return true;
        }
        return false;
    }

    private User getUserById(int userId) {
        Optional<User> rsl = users.stream()
                .filter(a -> a.getId() == userId).findFirst();
        if (rsl.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        return rsl.get();
    }
}
