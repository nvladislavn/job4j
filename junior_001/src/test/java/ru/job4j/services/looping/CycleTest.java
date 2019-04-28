package ru.job4j.services.looping;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CycleTest
 *
 * @author Vladislav Nechaev
 * @since 23.04.2019
 */
public class CycleTest {

    private Cycle cycle;
    private Node<Integer> first, two, third, four;

    @Before
    public void createCycle() {
        cycle = new Cycle();
        first = new Node<>(1);
        two = new Node<>(2);
        third = new Node<>(3);
        four = new Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
    }

    @Test
    public void shouldReturnFalse() {
        assertFalse(cycle.hasCycle(first));
    }

    @Test
    public void shouldReturnTrue() {
        third.next = two;
        assertTrue(cycle.hasCycle(first));
    }
}