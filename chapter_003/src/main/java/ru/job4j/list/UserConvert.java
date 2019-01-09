package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

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
     * @param list - the users list
     * @return - the users hashmap
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> users = new HashMap<>();
        if (list.isEmpty()) {
           throw new IllegalArgumentException("Users list is empty.");
        }
        for (User user : list) {
            users.put(user.getId(), user);
        }
        return users;
    }
}
