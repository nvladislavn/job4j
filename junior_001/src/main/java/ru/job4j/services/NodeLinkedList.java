package ru.job4j.services;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * NodeLinkedList
 *
 * @author Vladislav Nechaev
 * @since 21.04.2019
 */
public class NodeLinkedList<E> implements Iterable<E> {

    private Node<E> first;
    private int modCount;
    private int size;

    /**
     * add
     *
     * @param value - the item to add.
     */
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = first;
        first = newNode;
        modCount++;
        size++;
    }

    /**
     * get
     *
     * @param index - an index of getting item.
     * @return - value of the specified item.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        return getNode(index).value;
    }

    /**
     * getNode
     *
     * @param index - an index of getting item.
     * @return - the specified item.
     */
    private Node<E> getNode(int index) {
        Node<E> res = first;
        for (int i = 0; i < index; i++) {
            res = res.next;
        }
        return res;
    }

    /**
     * getSize
     *
     * @return - size of the nodeLinkedList.
     */
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int nextIndex;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return getNode(nextIndex) != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(nextIndex++);
            }
        };
    }

    private class Node<E> {

        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }
}
