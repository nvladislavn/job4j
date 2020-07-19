package ru.job4j.menu;

/**
 * Action
 *
 * @author Vladislav Nechaev
 * @since 01.07.2020
 */
public interface Action {

    /**
     * getName.
     * @return the action name.
     */
    String getName();

    /**
     * Executes the implemented code.
     */
    void execute();
}
