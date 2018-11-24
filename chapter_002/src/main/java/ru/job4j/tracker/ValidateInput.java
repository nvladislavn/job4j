package ru.job4j.tracker;

/**
 * ValidateInput.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 24.11.2018
 */
public class ValidateInput extends ConsoleInput {

    /**
     * ask. This method displays the message to the user and returns its response.
     * @param message - the message to the user.
     * @param range - an array with the range of menu items.
     * @return - the user's response.
     */
    @Override
    public int ask(String message, int[] range) {
        boolean invalid = true;
        int res = -1;
        do {
            try {
                res = super.ask(message, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return res;
    }
}
