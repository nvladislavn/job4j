package ru.job4j.services.store;

import ru.job4j.services.SimpleArray;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * AbstractStore
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> sa;

    public AbstractStore(int number) {
        sa = new SimpleArray<>(number);
    }

    /**
     * add
     *
     * @param model - add item.
     */
    @Override
    public void add(T model) {
        sa.add(model);
    }

    /**
     * replace
     *
     * @param id    - the field of child of Base class.
     * @param model - the new replacement item.
     * @return - true if the replacement succeeded.
     */
    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        if (index == -1) {
            return false;
        }
        sa.set(index, model);
        return sa.get(index) == model;
    }

    /**
     * delete
     *
     * @param id - the field of child of Base class.
     * @return - true if the removal succeeded.
     */
    @Override
    public boolean delete(String id) {
        int index = getIndex(id);
        if (index == -1) {
            return false;
        }
        var model = sa.get(index);
        sa.remove(index);
        return sa.get(index) != model;
    }

    /**
     * findById
     *
     * @param id - the field of child of Base class.
     * @return - found item.
     */
    @Override
    public T findById(String id) {
        int index = getIndex(id);
        if (index == -1) {
            return null;
        }
        return sa.get(getIndex(id));
    }

    /**
     * getIndex
     *
     * @param id - the field of child of Base class
     * @return - the index of array cell.
     */
    private int getIndex(String id) {
        int ind = -1;
        int count = 0;
        for (T t : sa) {
            if (t.getId().equals(id)) {
                ind = count;
                break;
            }
            count++;
        }
        return ind;
    }
}
