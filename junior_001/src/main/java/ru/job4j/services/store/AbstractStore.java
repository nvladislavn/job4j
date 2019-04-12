package ru.job4j.services.store;

import ru.job4j.services.SimpleArray;

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
     * @param id    - an index of array cell.
     * @param model - the new replacement item.
     * @return - true if the replacement succeeded.
     */
    @Override
    public boolean replace(String id, T model) {
        int index = Integer.parseInt(id);
        sa.set(index, model);
        return sa.get(index) == model;
    }

    /**
     * delete
     *
     * @param id - an index of array cell.
     * @return - true if the removal succeeded.
     */
    @Override
    public boolean delete(String id) {
        int index = Integer.parseInt(id);
        var model = sa.get(index);
        sa.remove(index);
        return sa.get(index) != model;
    }

    /**
     * findById
     *
     * @param id - an index of array cell.
     * @return - found item.
     */
    @Override
    public T findById(String id) {
        return sa.get(Integer.parseInt(id));
    }

}
