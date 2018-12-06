package ru.job4j.tracker.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * LazyTrackerTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.12.2018
 */
public class LazyTrackerTest {

    /**
     * Tests object creation of LazyTracker instance.
     */
    @Test
    public void shouldBeOnlyOneInstance() {
        LazyTracker lazyTrackerOne = LazyTracker.getLazyTracker();
        LazyTracker lazyTrackerSecond = LazyTracker.getLazyTracker();
        assertThat(lazyTrackerOne == lazyTrackerSecond, is(true));
    }
}