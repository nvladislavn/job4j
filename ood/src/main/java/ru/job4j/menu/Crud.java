package ru.job4j.menu;

/**
 * Crud
 *
 * @author Vladislav Nechaev
 * @since 08.07.2020
 */
public interface Crud<K, V> {

    boolean add(K parrent, K node, V action);

    boolean remove(K key);

    boolean update(K key, V action);

    V get(K key);

}
