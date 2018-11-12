package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 11.11.2018
 */
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    /**
     * ask. This method displays the message to the user and returns its response.
     *
     * @param message - the message to the user.
     * @return - the user's response.
     */
    public String ask(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

}
