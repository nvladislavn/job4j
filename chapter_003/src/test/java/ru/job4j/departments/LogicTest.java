package ru.job4j.departments;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * LogicTest
 *
 * @author Vladislav Nechaev
 * @since 03.02.2019
 */
public class LogicTest {

    Logic logic;
    private String[] unSort;

    @Before
    public void createLogic() {
        logic = new Logic();
        unSort = new String[]{"K1/SK1",
                            "K1/SK2",
                            "K1/SK1/SSK1",
                            "K1/SK1/SSK2",
                            "K2",
                            "K2/SK1/SSK1",
                            "K2/SK1/SSK2"};
    }

    /**
     * shouldIncreaseSorted
     * Tests sort.
     */
    @Test
    public void shouldIncreaseSorted() {
        String[] actual = logic.sort(this.unSort);
        String[] expected = {"K1",
                            "K1/SK1",
                            "K1/SK1/SSK1",
                            "K1/SK1/SSK2",
                            "K1/SK2",
                            "K2",
                            "K2/SK1",
                            "K2/SK1/SSK1",
                            "K2/SK1/SSK2"};
        assertThat(actual, is(expected));
    }

    /**
     * shouldDecreaseSorted
     * Tests reversSort.
     */
    @Test
    public void shouldDecreaseSorted() {
        String[] actual = logic.reversSort(this.unSort);
        String[] expected = {"K2",
                            "K2/SK1",
                            "K2/SK1/SSK2",
                            "K2/SK1/SSK1",
                            "K1",
                            "K1/SK2",
                            "K1/SK1",
                            "K1/SK1/SSK2",
                            "K1/SK1/SSK1"};
        assertThat(actual, is(expected));
    }
}