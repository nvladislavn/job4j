package ru.job4j.services.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node
 *
 * @author Vladislav Nechaev
 * @since 10.05.2019
 */
public class Node<E extends Comparable<E>> {

    private final List<Node<E>> children = new ArrayList<>();

    public E value() {
        return value;
    }

    private final E value;

    public Node(E value) {
        this.value = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}
