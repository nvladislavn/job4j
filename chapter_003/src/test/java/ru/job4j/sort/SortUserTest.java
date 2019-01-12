package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SortUserTest
 *
 * @author Vladislav Nechaev
 * @since 11.01.2019
 */
public class SortUserTest {

    /**
     * tests sort.
     */
    @Test
   public void whenJohnKateMichelleThenKateMichelleJohn() {
        SortUser sort = new SortUser();
        List<User> userList = new ArrayList<>(Arrays.asList(
                                                        new User("John", 45),
                                                            new User("Kate", 25),
                                                            new User("Michelle", 35)
                                                            ));
        String expected = "KateMichelleJohn";
        StringBuilder actual = new StringBuilder(16);
        for (User user : sort.sort(userList)) {
            actual.append(user.getName());
        }
        assertThat(actual.toString(), is(expected));
    }
}