package ru.job4j.list;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ConvertList2ArrayTest
 *
 * @author Vladislav Nechaev
 * @since 08.01.2019
 */
public class ConvertList2ArrayTest {

    /**
     * tests toArray
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array convert = new ConvertList2Array();
        int[][] actual = convert.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(actual, is(expected));
    }
}


