package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 30.12.2018
 */
public class LogicTest {

    /**
     * Tests getChange
     */
    @Test
    public void whenChange38Then101010521() {
        Logic logic = new Logic();
        assertThat(logic.getChange(41, 3), is(new int[]{10, 10, 10, 5, 2, 1}));
    }
}