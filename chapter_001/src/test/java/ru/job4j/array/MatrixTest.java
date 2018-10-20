package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MatrixTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 20/10/2018
 */
public class MatrixTest {

    /**
     * Test multiple method when size of the matrix 3x3
     */
    @Test
    public void when3on3() {
        Matrix matrix = new Matrix();
        int[][] resultTable = matrix.multiple(3);
        int[][] expectedTable = {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        assertThat(resultTable, is(expectedTable));
    }

    /**
     * Test multiple method when size of the matrix 0
     */
    @Test
    public void when0on0() {
        Matrix matrix = new Matrix();
        int[][] resultTable = matrix.multiple(0);
        int[][] expectedTable = {};
        assertThat(resultTable, is(expectedTable));
    }
}