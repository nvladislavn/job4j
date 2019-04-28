package ru.job4j.services;

import java.util.Iterator;

/**
 * SimpleSet
 *
 * @author Vladislav Nechaev
 * @since 24.04.2019
 */
public class SimpleSet<E> implements Iterable<E> {

    private DynamicList<E> dList;

    public SimpleSet(int size) {
        this.dList = new DynamicList<>(size);
    }


    @Override
    public Iterator iterator() {
        return null;
    }
}
