package ru.job4j.sort;

import java.util.*;
import java.util.stream.Collectors;

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
        return list
                .stream()
                .sorted(Comparator.comparingInt(u -> u.getName().length()))
                .collect(Collectors.toList());
    }

    /**
     * sortByFields
     * sorts list first by name, then by age.
     * @param list - users list.
     * @return - sorted users list.
     */
    public List<User> sortByFields(List<User> list) {
        return list
                .stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getAge))
                .collect(Collectors.toList());
    }
}
