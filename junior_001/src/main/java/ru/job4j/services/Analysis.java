package ru.job4j.services;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Analysis
 *
 * @author Vladislav Nechaev
 * @since 23.05.2019
 */
public class Analysis {

    /**
     * diff
     *
     * @param previous - the initial state list.
     * @param current  - the final state list.
     * @return - description of changes.
     */
    public Info diff(List<User> previous, List<User> current) {
        if (previous == null || current == null) {
            return null;
        }
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> currUserMap = current.stream()
                .collect(Collectors.toMap(u -> u.id, u -> u));
        for (User prevUser : previous) {
            User currUser = currUserMap.remove(prevUser.id);
            if (currUser == null) {
                deleted++;
            } else if (!currUser.name.equals(prevUser.name)) {
                changed++;
            }
        }
        Info info = new Info();
        info.added = currUserMap.size();
        info.deleted = deleted;
        info.changed = changed;
        return info;
    }

    public static class User {

        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id && name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
