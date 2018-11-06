package ru.job4j.profession;

/**
 * Student
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.11.2018
 */
public class Student {

    private String name;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
