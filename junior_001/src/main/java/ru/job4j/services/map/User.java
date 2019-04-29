package ru.job4j.services.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * User
 *
 * @author Vladislav Nechaev
 * @since 29.04.2019
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 0;
//        result = prime * result + (name == null ? 0 : name.hashCode());
//        result = prime * result + children;
//        result = prime * result + (birthday == null ? 0 : birthday.hashCode());
//        return result;
//    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User other = (User) obj;
        return (Objects.equals(other.getName(), name))
                && (other.children == children)
                && (Objects.equals(other.getBirthday(), birthday));
    }
}
