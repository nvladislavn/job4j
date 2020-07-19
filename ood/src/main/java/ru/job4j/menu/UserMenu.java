package ru.job4j.menu;

import java.util.*;

/**
 * UserMenu.
 *
 * @author Vladislav Nechaev
 * @since 15.07.2020
 */
public class UserMenu<K extends Comparable<K>> implements Menu<K> {

    private static final String PREFIX_SYMBOL = "-";
    private static final String INDENT = " ";
    private static final String LS = System.lineSeparator();
    private Node<K> root;

    public UserMenu(K key, Action action) {
        this.root = new Node<>(null, key, action);
    }

    /**
     * Adds a node to the storage.
     *
     * @param parrent - a parrent node (@see ru.job4j.menu.UserMenu.Node<K>).
     * @param child   - a child node (@see ru.job4j.menu.UserMenu.Node<K>).
     * @param action  - an action instance (@link ru.job4j.menu.Action).
     * @return - true if addition was successful, false otherwise.
     */
    @Override
    public boolean add(K parrent, K child, Action action) {
        boolean res;
        Optional<Node<K>> prt = findNode(parrent);
        if (prt.isEmpty() || prt.get().children
                                            .stream()
                                            .anyMatch(n -> n.key.equals(child))) {
            res = false;
        } else {
            prt.get().children.add(new Node<>(prt.get(), child, action));
            res = true;
        }
        return res;
    }

    /**
     * Removes a node with specified key.
     *
     * @param key - a node key.
     * @return true if removing od node was successful.
     */
    @Override
    public boolean remove(K key) {
        boolean res = true;
        Optional<Node<K>> node = findNode(key);
        if (node.isEmpty()) {
            res = false;
        } else if (root.equals(node.get())) {
            root = null;
        } else {
            Node<K> toDel = node.get();
            Node<K> parrent = toDel.parrent;
            parrent.children.addAll(toDel.children);
            parrent.children.remove(toDel);
        }
        return res;
    }

    /**
     * Updates an node action with specified key.
     *
     * @param key    - the node key.
     * @param action - an action to update.
     * @return true if updating a node action was successful.
     */
    @Override
    public boolean update(K key, Action action) {
        boolean res = false;
        Optional<Node<K>> toUpd = findNode(key);
        if (toUpd.isPresent()) {
            toUpd.get().action = action;
            res = true;
        }
        return res;
    }

    /**
     * Look for a node with specified key.
     *
     * @param key - the search key.
     * @return the Optional with the found node.
     */
    private Optional<Node<K>> findNode(K key) {
        Queue<Node<K>> nodes = new LinkedList<>();
        Optional<Node<K>> result = Optional.empty();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node<K> current = nodes.poll();
            if (key.equals(current.key)) {
                result = Optional.of(current);
                break;
            }
            nodes.addAll(current.children);
        }
        return result;
    }

    /**
     * Returns an action by the node with specified key.
     * @param key - the search key.
     * @return an action by the node with specified key if the search was successful, else - null.
     */
    @Override
    public Action get(K key) {
        Optional<Node<K>> node = findNode(key);
        return node.map(n -> n.action).orElse(null);
    }

    /**
     *
     * @return a string with menu text.
     */
    @Override
    public String unordered() {
        return unordered(0,
                root,
                new StringJoiner(LS)
        ).toString();
    }

    private StringJoiner unordered(int level, Node<K> node, StringJoiner joiner) {
        joiner.add(INDENT.repeat(level)
                        + PREFIX_SYMBOL.repeat(level)
                        + node.action.getName()
        );
        node.children.forEach(n -> unordered(level + 1, n, joiner));
        return joiner;
    }

    /**
     *
     * @return a string with numbered menu text.
     */
    @Override
    public String ordered() {
        return ordered(0,
                        "",
                        root,
                        new StringJoiner(LS)
                    ).toString();
    }

    private StringJoiner ordered(int level, String pref, Node<K> node, StringJoiner joiner) {
        String prx = level == 0 ? "" : INDENT + pref + level + ".";
        joiner.add(String.format(("%s %s"), prx, node.action.getName()));
        for (int i = 0; i < node.children.size(); i++) {
            ordered(i + 1, prx, node.children.get(i), joiner);
        }
        return joiner;
    }

    private static class Node<K> {

        private K key;
        private Action action;
        private Node<K> parrent;
        private List<Node<K>> children = new ArrayList<>();

        public Node(Node<K> parrent, K key, Action action) {
            this.parrent = parrent;
            this.key = key;
            this.action = action;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return key.equals(node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
