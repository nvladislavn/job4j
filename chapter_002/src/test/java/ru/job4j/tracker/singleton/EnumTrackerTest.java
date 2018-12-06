package ru.job4j.tracker.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * EnumTrackerTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 05.12.2018
 */
public class EnumTrackerTest {

    /**
     * Tests object creation of EnumTracker instance.
     */
    @Test
    public void shouldBeOnlyOneInstance() {
        EnumTracker enumTrackerOne = EnumTracker.INSTANCE;
        EnumTracker enumTrackerSecond = EnumTracker.INSTANCE;
        assertThat(enumTrackerOne == enumTrackerSecond, is(true));
    }

}