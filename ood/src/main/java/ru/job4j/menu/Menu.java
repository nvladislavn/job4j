package ru.job4j.menu;

import ru.job4j.menu.Action;

/**
 * Menu
 * extends the Crud and Printable interfaces.
 *
 * @author Vladislav Nechaev
 * @since 01.07.2020
 */
public interface Menu<K extends Comparable<K>> extends Crud<K, Action>, Printable {
}
