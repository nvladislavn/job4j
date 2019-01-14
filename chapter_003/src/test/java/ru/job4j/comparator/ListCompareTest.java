package ru.job4j.comparator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ListCompareTest
 *
 * @author Vladislav Nechaev
 * @since 14.01.2019
 */
public class ListCompareTest {

    private ListCompare comparator;

    @Before
    public void createComparator() {
        comparator = new ListCompare();
    }

    /**
     * tests compare
     */
    @Test
    public void whenStringsAreEqualThenZero() {
        int rst = this.comparator.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    /**
     * tests compare
     */
    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        int rst = this.comparator.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    /**
     * tests compare
     */
    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        int rst = this.comparator.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    /**
     * tests compare
     */
    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        int rst = this.comparator.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    /**
     * tests compare
     */
    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        int rst = this.comparator.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}