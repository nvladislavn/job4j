package ru.job4j.services;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * NodeLinkedListTest
 *
 * @author Vladislav Nechaev
 * @since 21.04.2019
 */
public class NodeLinkedListTest {

    @Test
    public void shouldReturn3And4() {
        NodeLinkedList<Integer> nodeList = new NodeLinkedList<>();
        nodeList.add(1);
        nodeList.add(2);
        nodeList.add(3);
        assertThat(nodeList.get(2), is(1));
        nodeList.add(4);
        assertThat(nodeList.get(0), is(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnException() {
        NodeLinkedList<Integer> nodeList = new NodeLinkedList<>();
        nodeList.add(1);
        nodeList.add(2);
        nodeList.add(3);
        assertThat(nodeList.get(3), is(4));
    }

    @Test
    public void shouldReturnFalse() {
        NodeLinkedList<Integer> nodeList = new NodeLinkedList<>();
        nodeList.add(1);
        nodeList.add(2);
        nodeList.add(3);
        var it = nodeList.iterator();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldReturnTrueAnd2() {
        NodeLinkedList<Integer> nodeList = new NodeLinkedList<>();
        nodeList.add(1);
        nodeList.add(2);
        nodeList.add(3);
        var it = nodeList.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnConcurrentModificationException() {
        NodeLinkedList<Integer> nodeList = new NodeLinkedList<>();
        var it = nodeList.iterator();
        nodeList.add(4);
        it.hasNext();
    }
}
