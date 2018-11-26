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
        StartUI startUI = new StartUI(
                                    new ValidateInput(
                                                    new ConsoleInput()
                                                    ),
                                    new Tracker()
                                    );
        startUI.init();
    }

    /**
     * init.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        int actionLength = menuTracker.getActionLength();
        int[] range = new int[actionLength];
        menuTracker.fillActions();
        for (int i = 0; i < actionLength; i++) {
            range[i] = i + 1;
        }
        do {
            menuTracker.show();
            int answer = input.ask("Please select: ", range);
            menuTracker.select(answer);
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }
}
