package ru.job4j.cuncurrentmap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Base> map = new HashMap<>();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        map.put(base1.getId(), base1);
        map.put(base2.getId(), base2);
    }
}
