package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        List<User> list = Arrays.asList(new User(1, "John", "LA"),
                                        new User(2, "Kate", "NY"));
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, new User(1, "John", "LA"));
        expected.put(2, new User(2, "Kate", "NY"));
        assertThat(new UserConvert().process(list), is(expected));
    }

}