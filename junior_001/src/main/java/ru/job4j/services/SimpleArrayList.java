package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArrayList
 *
 * @author Vladislav Nechaev
 * @since 16.04.2019
 */
public class SimpleArrayList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;

    /**
     * add
     *
     * @param date - add item.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * delete
     *
     * @return - deleted item.
     */
    public E delete() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E temp = first.date;
        first = first.next;
        size--;
        return temp;
    }

    /**
     * get
     *
     * @param index - item index.
     * @return - the specified item.
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * size
     *
     * @return - size of the SimpleArrayList.
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private int currentIndex;

            @Override
            public boolean hasNext() {
                Node<E> node = first;
                for (int i = 0; i < currentIndex; i++) {
                    node = node.next;
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(currentIndex++);
            }
        };
    }

    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
