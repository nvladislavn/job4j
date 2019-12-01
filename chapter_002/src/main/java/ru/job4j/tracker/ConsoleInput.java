package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 11.11.2018
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    /**
     * ask. This method displays the message to the user and returns its response.
     *
     * @param message - the message to the user.
     * @return - the user's response.
     */
    @Override
    public String ask(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * ask. This method displays the message to the user and returns its response.
     * @param message - the message to the user.
     * @param range - an array with the range of menu ru.job4j.items.
     * @return - the user's response.
     */
    @Override
    public int ask(String message, int[] range) {
        int key = Integer.valueOf(this.ask(message));
        boolean exist = false;
        for (int item : range) {
            if (key == item) {
                exist = true;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range.");
        }
        return key;
    }
}
