package ru.job4j;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TransformationTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 26.02.2019
 */
public class TransformationTest {

    /**
     * tests matrixToList.
     */
    @Test
    public void shouldBe123456() {
        Integer[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
        assertThat(new Transformation().matrixToList(matrix), is(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

}