package ru.job4j.services.tree;


import java.util.Optional;

/**
 * SimpleTree
 *
 * @author Vladislav Nechaev
 * @since 10.05.2019
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}
