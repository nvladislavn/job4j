package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Tracker
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 08.11.2018
 */
public class Tracker {

    private static final Random RAND = new Random();
    private final int arrayLength = 100;
    private List<Item> items = new ArrayList<>(arrayLength);

    /**
     * The method add.
     *
     * @param item - the instance of Item.
     * @return the instance of Item with id.
     */
    public Item add(Item item) {
        if (item != null) {
            item.setId(this.generateId());
            this.items.add(item);
        }
        return item;
    }

    /**
     * generateId.
     *
     * @return - id.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RAND.nextInt());
    }

    /**
     * replace. This method changes item with the specified id.
     *
     * @param id
     * @param item
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
            result = true;
        }
        return result;
    }

    /**
     * delete. This method deletes the item with the specified id.
     *
     * @param id
     */
    public boolean delete(String id) {
        boolean result = false;
        int delIndex = findIndexById(id);
        if (delIndex != -1) {
            this.items.remove(delIndex);
            result = true;
        }
        return result;
    }

    /**
     * findAll. This method returns the array non-null objects.
     *
     * @return the array non-null objects.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * findByName. This method returns the array of items with the specified name.
     *
     * @param key - the given name.
     * @return the array of items with the given name.
     */
    public List<Item> findByName(String key) {
        List<Item> nameList = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                nameList.add(item);
            }
        }
        return nameList;
    }

    /**
     * findById. This method returns the item with the specified id.
     *
     * @param id
     * @return the item with the specified id.
     */
    public Item findById(String id) {
        Item foundItem = null;
        int foundIndex = findIndexById(id);
        if (foundIndex != -1) {
            foundItem = items.get(foundIndex);
        }
        return foundItem;
    }

    /**
     * Method findIndexById
     * finds the index of the array item by id.
     *
     * @param id
     * @return index of the array.
     */
    private int findIndexById(String id) {
        int foundIndex = -1;
        if (!id.equals("")) {
            for (Item item : this.items) {
                if (item.getId().equals(id)) {
                    foundIndex = this.items.indexOf(item);
                    break;
                }
            }
        }
        return foundIndex;
    }
}
