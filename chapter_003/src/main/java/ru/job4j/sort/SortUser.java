package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
}
