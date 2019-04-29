package ru.job4j.services;

import java.util.Iterator;

/**
 * SimpleSet
 *
 * @author Vladislav Nechaev
 * @since 27.04.2019
 */
public class SimpleSet<E> implements Iterable<E> {

    private DynamicList<E> dList;

    public SimpleSet(int size) {
        this.dList = new DynamicList<>(size);
    }

    /**
     * add
     *
     * @param date - the item to add.
     */
    public void add(E date) {
        if (!hasItem(date)) {
            dList.add(date);
        }
    }

    /**
     * hasItem
     *
     * @param date - check item.
     * @return - true if the item already exists in the set.
     */
    private boolean hasItem(E date) {
        boolean exist = false;
        for (E item : dList) {
            if (item == null) {
                exist = (date == null);
            } else if (item.equals(date)) {
                exist = true;
            }
        }
        return exist;
    }

    @Override
    public Iterator<E> iterator() {
        return dList.iterator();
    }
}
