package ru.job4j.profession;

/**
 * Profession
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.11.2018
 */
public class Profession {

    private String name;
    private String profession;

    public Profession() {

    }

    public Profession(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
