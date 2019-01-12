package ru.job4j.sort;

import java.util.*;

/**
 * SortUser
 *
 * @author Vladislav Nechaev
 * @since 11.01.2019
 */
public class SortUser {

    /**
     * sort
     * sorts users list by user age.
     *
     * @param list - users list.
     * @return - the sorted TreeSet by user age.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * sortNameLength.
     * sorted users list by name length.
     *
     * @param list - the users list.
     * @return - the sorted users list.
     */
    public List<User> sortNameLength(List<User> list) {
        Comparator<User> comp = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.compare(user1.getName().length(), user2.getName().length());
            }
        };
        Collections.sort(list, comp);
        return list;
    }

    /**
     * sortByFields
     * sorts list first by name, then by age.
     * @param list - users list.
     * @return - sorted users list.
     */
    public List<User> sortByFields(List<User> list) {
        Comparator<User> nameComparator = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getName().compareTo(user2.getName());
            }
        };
        Comparator<User> ageComparator = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.compare(user1.getAge(), user2.getAge());
            }
        };
        Comparator<User> comp = nameComparator.thenComparing(ageComparator);
        Collections.sort(list, comp);
        return list;
    }
}
