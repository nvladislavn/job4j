package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ConvertMatrix2ListTest
 *
 * @author Vladislav Nechaev
 * @since 09.01.2019
 */
public class ConvertMatrix2ListTest {

    /**
     * when2on2ArrayThenList4
     *
     * tests toList
     */
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List convert = new ConvertMatrix2List();
        int[][] input = {{1, 2}, {3, 4}};
        List<Integer> expect = List.of(1, 2, 3, 4);
        List<Integer> result = convert.toList(input);
        assertThat(result, is(expect));
    }
}