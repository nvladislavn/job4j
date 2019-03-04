package ru.job4j.list;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserConvert
 *
 * @author Vladislav Nechaev
 * @since 09.01.2019
 */
public class UserConvert {

    /**
     * process
     * fills a hashmap by users from the List
     *
     * @param list - the users list
     * @return - the users hashmap
     */
    public Map<Integer, User> process(List<User> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Users list is empty.");
        }
        return list
                .stream()
                .collect(Collectors.toMap(User::getId, user -> user));
    }
}
