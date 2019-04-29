package ru.job4j.services.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * UserMap
 *
 * @author Vladislav Nechaev
 * @since 29.04.2019
 */
public class UserMap {

    private Map<User, Object> userMap = new HashMap<>();

    public void put(User user, Object object) {
        userMap.put(user, object);
    }

    public void printMap() {
        System.out.println(userMap);
    }

    public static void main(String[] args) {
        var um = new UserMap();
        var user1 = new User("John", 2, new GregorianCalendar(1980, Calendar.APRIL, 25));
        var user2 = new User("John", 2, new GregorianCalendar(1980, Calendar.APRIL, 25));
        um.put(user1,null);
        um.put(user2,null);
        System.out.println(String.format("hashCode user1 = %d", user1.hashCode()));
        System.out.println(String.format("hashCode user2 = %d", user2.hashCode()));
        um.printMap();
    }
}
