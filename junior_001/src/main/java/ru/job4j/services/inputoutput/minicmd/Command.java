package ru.job4j.services.inputoutput.minicmd;

/**
 * Command
 *
 * @author Vladislav Nechaev
 * @since 16.08.2019
 */
public enum Command {

    DISPLAY_CURRENT_DIRECTORY("^((cd)|(CD))$"),
    GOTO_THE_PARENT("^((cd)|(CD))\\.\\.$"),
    GOTO_DOWN("^((cd)|(CD))\\s+([-_!a-zA-Zа-яА-я0-9(^(/d)|(^(/D)))]+)"),
    GOTO_OTHER_DISK("^((cd)|(CD))\\s+((/d)|(/D))\\s+.+");

    private final String regexOfCommand;

    Command(String regexOfCommand) {
        this.regexOfCommand = regexOfCommand;
    }

    public String regex() {
        return regexOfCommand;
    }
}

