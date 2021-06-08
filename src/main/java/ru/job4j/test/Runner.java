package ru.job4j.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        User user1 = new User("pompom", 123);
        System.out.println(user1.toString());
        User user2 = changeUser(user1, "dddd", 321);
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        List<String> l = new ArrayList<>();
        
    }

    public static User changeUser(User user, String name, int age) {
        user.setName(name);
        user.setAge(age);
        return user;
    }

}

class User {
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
