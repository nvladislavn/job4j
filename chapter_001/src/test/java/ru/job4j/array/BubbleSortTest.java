package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BubbleSortTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 19/10/2018
 */
public class BubbleSortTest {

    /**
     * Test sort method
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bs = new BubbleSort();
        int[] array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 5, 1};
        int[] resArray = bs.sort(array);
        assertThat(resArray, is(new int[]{1, 3, 4, 5, 5, 6, 7, 8, 9, 10}));
    }
}