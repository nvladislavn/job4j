package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MyPriorityQueueTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 07.01.2018
 */
public class MyPriorityQueueTest {

    /**
     * whenHigherPriority
     * tests put
     */
    @Test
    public void whenHigherPriority() {
        MyPriorityQueue queue = new MyPriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}