package ru.job4j.tracker;

import java.util.List;
import java.util.Random;

/**
 * ITracker
 *
 * @author Vladislav Nechaev
 * @since 12.09.2019
 */
public interface ITracker {

    /**
     * generateId.
     *
     * @return - id.
     */
    default String generateId() {
        return String.valueOf(System.currentTimeMillis() + new Random().nextInt());
    }

    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
