package ru.job4j.school;

/**
 * Student
 *
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class Student {
    private String surname;
    private String name;
    private int score;


    public Student(String surname, String name, int score) {
        this.surname = surname;
        this.name = name;
        this.score = score;
    }

    /**
     * getScore
     * @return - the student score.
     */
    public int getScore() {
        return score;
    }

    /**
     * setScore
     *
     * @param score - the student score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }
}
