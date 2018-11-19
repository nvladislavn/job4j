package ru.job4j.tracker;

/**
 * StartUI.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 11.11.2018
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;

    /**
     * StartUI. Creating an instance of StartUI class.
     *
     * @param input   - an instance of ConsoleInput class.
     * @param tracker - an instance of Tracker class.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(new ConsoleInput(), new Tracker());
        startUI.init();
    }

    /**
     * init.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        menuTracker.fillActions();
        do {
            menuTracker.show();
            int answer = Integer.valueOf(input.ask("Please select: "));
            menuTracker.select(answer);
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }
}
