package ru.job4j.services.store;

/**
 * Base
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    /**
     * getId
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }
}
