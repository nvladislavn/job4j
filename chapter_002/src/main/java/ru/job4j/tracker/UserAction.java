package ru.job4j.tracker;

/**
 * UserAction.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 19.11.2018
 */
public interface UserAction {

    String getKey();

    void execute(Input input, Tracker tracker);

    String info();
}
