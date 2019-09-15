package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * StartUI.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 11.11.2018
 */
public class StartUI {
    private final Input input;
    private final ITracker tracker;
    private final Consumer<String> output;

    /**
     * StartUI. Creating an instance of StartUI class.
     *
     * @param input   - an instance of ConsoleInput class.
     * @param tracker - an instance of Tracker class.
     */
    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * init.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker, this.output);
        menuTracker.fillActions();
        int actionLength = menuTracker.getActionLength();
        int[] range = new int[actionLength];
        for (int i = 0; i < actionLength; i++) {
            range[i] = i;
        }
        do {
            menuTracker.show();
            int answer = input.ask("Please select: ", range);
            menuTracker.select(answer);
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }
}
