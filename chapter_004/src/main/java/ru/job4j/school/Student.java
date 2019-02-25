package ru.job4j.school;

/**
 * Student
 *
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class Student {
    private int score;


    public Student(int score) {
        this.score = score;
    }

    /**
     * getScore
     * @return - the student score.
     */
    public int getScore() {
        return score;
    }
}
