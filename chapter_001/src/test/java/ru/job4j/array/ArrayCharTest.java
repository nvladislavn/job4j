package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ArrayCharTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class ArrayCharTest {

    /**
     * Test startWith method when the prefix matches
     */
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        assertThat(word.startWith("He"), is(true));
    }

    /**
     * Test startWith method when the prefix does not matches
     */
    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        assertThat(word.startWith("Hi"), is(false));
    }

    /**
     * Test startWith method when the prefix is empty
     */
    @Test
    public void whenStartWithEmptyPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        assertThat(word.startWith(""), is(true));
    }

    /**
     * Test startWith method when the word is empty
     */
    @Test
    public void whenStartWithEmptyWordThenTrue() {
        ArrayChar word = new ArrayChar("");
        assertThat(word.startWith("Hi"), is(true));
    }
}