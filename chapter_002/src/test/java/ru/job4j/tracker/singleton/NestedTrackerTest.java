package ru.job4j.tracker.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * NestedTrackerTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 05.12.2018
 */
public class NestedTrackerTest {

    /**
     * Tests object creation of NestedTracker instance.
     */
    @Test
    public void shouldBeOnlyOneInstance() {
        NestedTracker nestedTrackerOne = NestedTracker.getNestedTracker();
        NestedTracker nestedTrackerSecond = NestedTracker.getNestedTracker();
        assertThat(nestedTrackerOne == nestedTrackerSecond, is(true));
    }
}