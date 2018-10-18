package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TurnTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class CheckTest {

    /**
     * Test check method
     */
    @Test
    public void whenTrueFalseTrueThenFalse() {
        Check check = new Check();
        boolean result = check.mono(new boolean[]{true, false, true});
        assertThat(result, is(false));
    }

    /**
     * Test check method
     */
    @Test
    public void whenTrueTrueTrueThenTrue() {
        Check check = new Check();
        boolean result = check.mono(new boolean[]{true, true, true});
        assertThat(result, is(true));
    }

    /**
     * Test check method
     */
    @Test
    public void whenFalseFalseFalseThenTrue() {
        Check check = new Check();
        boolean result = check.mono(new boolean[]{false, false, false});
        assertThat(result, is(true));
    }

    /**
     * Test check method when the aray is empty
     */
    @Test
    public void whenArrayIsEmptyThenFalse() {
        Check check = new Check();
        boolean result = check.mono(new boolean[]{});
        assertThat(result, is(false));
    }

}