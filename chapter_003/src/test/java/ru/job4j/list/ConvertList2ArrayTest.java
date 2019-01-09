package ru.job4j.list;

import org.junit.Before;
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

    private ConvertList2Array convert;

    @Before
    public void createConvert() {
        convert = new ConvertList2Array();
    }

    /**
     * tests toArray
     */
    @Test
    public void when7ElementsThen9() {
        int[][] actual = convert.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(actual, is(expected));
    }

    /**
     * tests convert
     */
    @Test
    public void shouldBe123456() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5});
        list.add(new int[]{6});
        assertThat(convert.convert(list), is(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6))));
    }

}


