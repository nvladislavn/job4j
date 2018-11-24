package ru.job4j.tracker;

/**
 * StubInput.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 12.11.2018
 */
public class StubInput implements Input {

    private int position;
    private String[] answers;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * ask. This method returns an answer from the specified answer array.
     *
     * @param message - message to user.
     * @return - answer from the specified answer array.
     */
    @Override
    public String ask(String message) {
        return answers[position++];
    }

    @Override
    public int ask(String message, int[] range) {
        return -1;
    }
}
