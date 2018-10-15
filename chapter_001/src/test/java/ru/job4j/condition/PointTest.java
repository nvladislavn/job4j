package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 *Test
 *@author Vladislav Nechaev
 *@version $Id$
 *@since 15/10/2018
 */
public class PointTest {

    /**
     * Test distanceTo
     */
    @Test
    public void distanceTo() {
      Point a = new Point(0, 1);
      Point b = new Point(2, 5);
      assertThat(a.distanceTo(b), closeTo(4.472, 0.1));
    }
}