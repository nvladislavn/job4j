package ru.job4j.services.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ConcreteSimpleTreeTest
 *
 * @author Vladislav Nechaev
 * @since 14.05.2019
 */
public class ConcreteSimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void shouldReturnTrue1True2True3False() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator<Integer> it = tree.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldReturnTrue() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        assertTrue(tree.add(1, 3));
    }

    @Test
    public void shouldReturnFalse() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        assertFalse(tree.add(2, 3));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowsNoSuchElementException() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(3, 4);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator<Integer> it = tree.iterator();
        tree.add(2, 4);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddNonExistentParentThenThrowsNoSuchElementException() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 3);
    }

    @Test
    public void whenIsBinaryThenReturnTrue() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenisNonBinaryThenReturnFalse() {
        SimpleTree<Integer> tree = new ConcreteSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        assertFalse(tree.isBinary());
    }
}