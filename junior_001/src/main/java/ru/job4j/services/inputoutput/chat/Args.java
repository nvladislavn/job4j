package ru.job4j.services.inputoutput.chat;

/**
 * Args
 *
 * @author Vladislav Nechaev
 * @since 07.07.2019
 */
public class Args {

    private String[] arguments;

    public Args(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * getAnswersPath
     *
     * @return - the path to the answers file.
     */
    public String getAnswersPath() {
        return arguments[0];
    }

    /**
     * getLogPath
     *
     * @return - the path to the log file.
     */
    public String getLogPath() {
        return arguments[1];
    }
}
