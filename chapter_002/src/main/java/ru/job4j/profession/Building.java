package ru.job4j.profession;

/**
 * Building
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.11.2018
 */
public class Building {

    private String name;

    public Building() {
    }

    public Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
