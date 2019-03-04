package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * shouldReturns1John2Kate
 *
 * @author Vladislav Nechaev
 * @since 09.01.2019
 */
public class UserConvertTest {

    /**
     * tests process
     */

    @Test
    public void shouldReturns1John2Kate() {
        List<User> list = List.of(new User(1, "John", "LA"), new User(2, "Kate", "NY"));
        Map<Integer, User> expected = Map.of(1, new User(1, "John", "LA"), 2, new User(2, "Kate", "NY"));
        assertThat(new UserConvert().process(list), is(expected));
    }
}