package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * MatrixCheckTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 20/10/2018
 */
public class MatrixCheckTest {

    /**
     * Test mono method
     */
    @Test
    public void whenOddDataMonoByTrueThenTrue() {
        MatrixCheck mCh = new MatrixCheck();
        boolean[][] inputArray = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        boolean result = mCh.mono(inputArray);
        assertThat(result, is(true));
    }

    /**
     * Test mono method
     */
    @Test
    public void whenOddDataMonoByFalseThenFalse() {
        MatrixCheck mCh = new MatrixCheck();
        boolean[][] inputArray = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {false, true, true}
        };
        boolean result = mCh.mono(inputArray);
        assertThat(result, is(false));
    }

    /**
     * Test mono method
     */
    @Test
    public void whenEvenDataMonoByFalseThenFalse() {
        MatrixCheck mCh = new MatrixCheck();
        boolean[][] inputArray = new boolean[][]{
                {true, true},
                {false, true}
        };
        boolean result = mCh.mono(inputArray);
        assertThat(result, is(false));
    }

    /**
     * Test mono method
     */
    @Test
    public void whenEvenDataMonoByTrueThenTrue() {
        MatrixCheck mCh = new MatrixCheck();
        boolean[][] inputArray = new boolean[][]{
                {true, true},
                {true, true}
        };
        boolean result = mCh.mono(inputArray);
        assertThat(result, is(true));
    }
}