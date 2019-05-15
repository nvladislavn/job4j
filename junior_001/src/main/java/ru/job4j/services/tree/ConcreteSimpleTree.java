package ru.job4j.services.tree;

import java.util.*;

/**
 * ConcreteSimpleTree
 *
 * @author Vladislav Nechaev
 * @since 13.05.2019
 */
public class ConcreteSimpleTree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int modCount;
    private int size;

    public ConcreteSimpleTree(E value) {
        this.root = new Node<>(value);
        size++;
    }

    /**
     * add
     *
     * @param parent - the specified parent.
     * @param child  - the specified child.
     * @return - true if the specified child was successfully added.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean added = false;
        Optional<Node<E>> prnNode = findBy(parent);
        Optional<Node<E>> chdNode = findBy(child);
        if (chdNode.isEmpty() && prnNode.isPresent()) {
            prnNode.get().add(new Node<>(child));
            size++;
            added = true;
            modCount++;
        } else if (prnNode.isEmpty()) {
            throw new NoSuchElementException("The specified parent is not exists.");
        }
        return added;
    }

    /**
     * findBy
     *
     * @param value - sought value.
     * @return - found Node.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                rsl = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * size
     *
     * @return - size of the tree.
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = 0;
            Node<E> currentNode = root;
            Queue<Node<E>> nodeQueue = new LinkedList<>();
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                nodeQueue.offer(currentNode);
                E res = null;
                while (!nodeQueue.isEmpty()) {
                    currentNode = nodeQueue.poll();
                    List<Node<E>> children = currentNode.leaves();
                    if (!children.isEmpty()) {
                        for (Node<E> item : children) {
                            nodeQueue.offer(item);
                        }
                    }
                    currentIndex++;
                    res = currentNode.value();
                    break;
                }
                return res;
            }
        };
    }
}
