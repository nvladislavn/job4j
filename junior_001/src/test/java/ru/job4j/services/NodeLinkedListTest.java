package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
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


    private NodeLinkedList nodeList;
    private Iterator it;

    @Before
    public void createDynamicList() {
        nodeList = new NodeLinkedList();
        nodeList.add(1);
        nodeList.add(2);
        nodeList.add(3);
        it = nodeList.iterator();
    }

    @Test
    public void shouldReturn3And4() {
        assertThat(nodeList.get(2), is(1));
        nodeList.add(4);
        assertThat(nodeList.get(0), is(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnException() {
        assertThat(nodeList.get(3), is(4));
    }

    @Test
    public void shouldReturnFalse() {
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void shouldReturnTrueAnd2() {
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnConcurrentModificationException() {
        nodeList.add(4);
        it.hasNext();
    }
}