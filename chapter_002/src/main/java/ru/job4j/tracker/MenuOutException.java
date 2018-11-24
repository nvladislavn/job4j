package ru.job4j.tracker;

/**
 * MenuOutException.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 24.11.2018
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException(String message) {
        super(message);
    }
}
