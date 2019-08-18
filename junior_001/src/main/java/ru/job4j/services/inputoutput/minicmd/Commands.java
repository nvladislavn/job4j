package ru.job4j.services.inputoutput.minicmd;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Commands
 *
 * @author Vladislav Nechaev
 * @since 16.08.2019
 */
public class Commands {

    private Map<String, BiFunction<File, String, String>> commands = new HashMap<>();

    public Commands() {
        fieldTheCommands();
    }

    /**
     * getHandler
     *
     * @param command - command to handle.
     * @return - BiFunction<File, String, String>.
     * @throws IllegalCommandException
     */
    public BiFunction<File, String, String> getHandler(String command) throws IllegalCommandException {
        BiFunction<File, String, String> res = null;
        for (String comm : commands.keySet()) {
            if (command.matches(comm)) {
                res = commands.get(comm);
                break;
            }
        }
        if (res == null) {
            throw new IllegalCommandException(command);
        }
        return res;
    }

    /**
     * fieldTheCommands
     * fields commands.
     */
    private void fieldTheCommands() {
        commands.put(
                Command.DISPLAY_CURRENT_DIRECTORY.regex(),
                (currentFile, command) -> currentFile.getPath()
        );
        commands.put(
                Command.GOTO_THE_PARENT.regex(),
                (currentFile, command) -> currentFile.getParent()
        );
        commands.put(
                Command.GOTO_DOWN.regex(),
                (currentFile, command) -> currentFile + "\\" + command.strip().substring(3).strip()
        );
        commands.put(
                Command.GOTO_OTHER_DISK.regex(),
                (currentFile, command) -> command.strip().substring(6).strip()
        );
    }
}


/**
 * IllegalCommandException
 *
 * @author Vladislav Nechaev
 * @since 15.08.2019
 */
class IllegalCommandException extends Exception {

    private String command;

    public IllegalCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("Command \"%s\" is not exists.", command);
    }
}
