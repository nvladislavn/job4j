package ru.job4j.tracker;

import java.util.Arrays;
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
    private int position = 0;
    private final int arrayLength = 100;
    private Item[] items = new Item[arrayLength];

    /**
     * The method add.
     *
     * @param item - the instance of Item.
     * @return the instance of Item with id.
     */
    public Item add(Item item) {
        if (item != null) {
            item.setId(this.generateId());
            items[position++] = item;
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
     * replace. This method changes item with the given id.
     *
     * @param id
     * @param item
     */
    public void replace(String id, Item item) {
        int index = findIndexById(id);
        if (index != -1) {
            item.setId(id);
            items[index] = item;
        }
    }

    /**
     * delete. This method deletes the item with the given id.
     *
     * @param id
     */
    public void delete(String id) {
        int delIndex = findIndexById(id);
        if (delIndex != -1) {
            System.arraycopy(items, delIndex + 1, items, delIndex, items.length - 1 - delIndex);
            position--;
        }
    }

    /**
     * findAll. This method returns the array non-null objects.
     *
     * @return the array non-null objects.
     */
    public Item[] findAll() {
        return (Arrays.copyOf(this.items, this.position));
    }

    /**
     * findByName. This method returns the array of items with the given name.
     *
     * @param key - the given name.
     * @return the array of items with the given name.
     */
    public Item[] findByName(String key) {
        Item[] nameArray = new Item[position];
        int newIndex = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(key)) {
                nameArray[newIndex] = this.items[i];
                newIndex++;
            }
        }
        return (Arrays.copyOf(nameArray, newIndex));
    }

    /**
     * findById. This method returns the item with the given id.
     *
     * @param id
     * @return the item with the given id.
     */
    public Item findById(String id) {
        Item foundItem = null;
        int foundIndex = findIndexById(id);
        if (foundIndex != -1) {
            foundItem = items[foundIndex];
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
            for (int i = 0; i < position; i++) {
                if (items[i].getId().equals(id)) {
                    foundIndex = i;
                    break;
                }
            }
        }
        return foundIndex;
    }
}
