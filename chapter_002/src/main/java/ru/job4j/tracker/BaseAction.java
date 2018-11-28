package ru.job4j.tracker;

/**
 * BaseAction.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 28.11.2018
 */
abstract class BaseAction implements UserAction {

    private String key;
    private String name;

    protected BaseAction(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * getKey.
     * This method returns an action key.
     * @return - an action key.
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * info.
     * This method returns a string representation of the action.
     * @return - a string representation of the action.
     */
    @Override
    public String info() {
        return String.format("%s : %s", this.key, this.name);
    }
}
