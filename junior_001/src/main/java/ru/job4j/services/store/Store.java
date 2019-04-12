package ru.job4j.services.store;

/**
 * Store
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
