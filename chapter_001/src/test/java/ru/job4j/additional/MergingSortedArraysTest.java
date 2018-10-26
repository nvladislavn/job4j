package ru.job4j.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MergingSortedArraysTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 26/10/2018
 */
public class MergingSortedArraysTest {

    /**
     * test merge method
     * when the first array is larger than the second
     */
    @Test
    public void whenFirstArrayIsLarger() {
        MergingSortedArrays mSA = new MergingSortedArrays();
        int[] firstArray = {1, 3, 5, 5};
        int[] secondtArray = {0, 2, 4};
        int[] result = mSA.merge(firstArray, secondtArray);
        assertThat(result, is(new int[]{0, 1, 2, 3, 4, 5, 5}));
    }

    /**
     * test merge method
     * when the second array is larger than the first
     */
    @Test
    public void whenSecondArrayIsLarger() {
        MergingSortedArrays mSA = new MergingSortedArrays();
        int[] firstArray = {1, 3, 5};
        int[] secondtArray = {0, 2, 2, 4};
        int[] result = mSA.merge(firstArray, secondtArray);
        assertThat(result, is(new int[]{0, 1, 2, 2, 3, 4, 5}));
    }

    /**
     * test merge method
     * when the array lengths're equal
     */
    @Test
    public void whenArrayLengthsAreEqual() {
        MergingSortedArrays mSA = new MergingSortedArrays();
        int[] firstArray = {1, 3, 5};
        int[] secondtArray = {0, 2, 3};
        int[] result = mSA.merge(firstArray, secondtArray);
        assertThat(result, is(new int[]{0, 1, 2, 3, 3, 5}));
    }
}