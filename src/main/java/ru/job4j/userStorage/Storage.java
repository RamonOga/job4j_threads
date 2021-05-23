package ru.job4j.userStorage;

public interface Storage {
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
    boolean transfer(int fromId, int told, int amount);

}
