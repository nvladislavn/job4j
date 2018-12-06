package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * EagerTrackerTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.12.2018
 */
public class EagerTrackerTest {

    Tracker tracker = new Tracker();

    /**
     * Tests object creation of EagerTracker instance.
     */
    @Test
    public void shouldBeOnlyOneInstance() {
        EagerTracker eagerTrackerOne = EagerTracker.getEagerTracker();
        EagerTracker eagerTrackerSecond = EagerTracker.getEagerTracker();
        assertThat(eagerTrackerOne == eagerTrackerSecond, is(true));
    }

}