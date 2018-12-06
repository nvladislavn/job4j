package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * EagerTracker
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.12.2018
 */
public class EagerTracker {

    private Tracker tracker = new Tracker();
    private static final EagerTracker EAGER_TRACKER = new EagerTracker();

    private EagerTracker() {
    }

    /**
     * getEagerTracker
     *
     * this method returns an instance of EagerTracker.
     * @return - an instance of EagerTracker.
     */
    public static EagerTracker getEagerTracker() {
        return EAGER_TRACKER;
    }

    /**
     * The method add.
     *
     * @param item - the instance of Item.
     * @return the instance of Item with id.
     */
    public Item add(Item item) {
        return tracker.add(item);
    }

    /**
     * replace. This method changes item with the given id.
     *
     * @param id
     * @param item
     */
    public boolean replace(String id, Item item) {
        return tracker.replace(id, item);
    }

    /**
     * delete. This method deletes the item with the given id.
     *
     * @param id
     */
    public boolean delete(String id) {
        return tracker.delete(id);
    }

    /**
     * findAll. This method returns the array non-null objects.
     *
     * @return the array non-null objects.
     */
    public Item[] findAll() {
        return tracker.findAll();
    }

    /**
     * findByName. This method returns the array of items with the given name.
     *
     * @param key - the given name.
     * @return the array of items with the given name.
     */
    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    /**
     * findById. This method returns the item with the given id.
     *
     * @param id
     * @return the item with the given id.
     */
    public Item findById(String id) {
        return tracker.findById(id);
    }
}
