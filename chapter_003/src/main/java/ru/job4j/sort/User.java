package ru.job4j.sort;

import java.util.Objects;

/**
 * User
 *
 * @author Vladislav Nechaev
 * @since 11.01.2019
 */
public class User implements Comparable<User> {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User user) {
        return Integer.compare(this.age, user.getAge());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.getAge() && Objects.equals(name, user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
