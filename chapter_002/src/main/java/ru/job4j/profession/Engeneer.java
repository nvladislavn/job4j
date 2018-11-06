package ru.job4j.profession;

/**
 * Engeneer
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.11.2018
 */
public class Engeneer extends Profession {

    /**
     * cure method
     * @param building
     * @return when building is successful then true, else false.
     */
    public boolean build(Building building) {
        return true;
    }
}
