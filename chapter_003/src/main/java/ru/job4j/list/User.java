package ru.job4j.list;

import java.util.Objects;

/**
 * User
 *
 * @author Vladislav Nechaev
 * @since 09.01.2019
 */
public class User {

    private int id;
    private String name;
    private String sity;

    public User(int id, String name, String sity) {
        this.id = id;
        this.name = name;
        this.sity = sity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSity() {
        return sity;
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
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(sity, user.sity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sity);
    }
}
