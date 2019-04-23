package ru.job4j.services.looping;

/**
 * Node
 *
 * @author Vladislav Nechaev
 * @since 23.04.2019
 */
class Node<T> {

    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}
