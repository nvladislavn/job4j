package ru.job4j.services.inputoutput.minicmd;

import java.io.File;

/**
 * Shell
 *
 * @author Vladislav Nechaev
 * @since 22.08.2019
 */
public class Shell {

    private File currentFile;
    private Commands commands = new Commands();

    public Shell(String startDirectory) {
        this.currentFile = new File(startDirectory);
    }

    /**
     * cd
     *
     * @param path - command cmd with target path.
     * @return - the instance of Shell.
     * @throws IllegalCommandException
     */
    Shell cd(final String path) throws IllegalCommandException {
        String newPath = null;
            newPath = commands.getHandler(path).apply(currentFile, path);
        File tempFile = new File(newPath);
        if (!tempFile.exists() || !tempFile.isDirectory()) {
            throw new IllegalCommandException(
                    String.format("Ditectory \"%s\" is not exists. Please enter correct path.", newPath)
            );
        }
        currentFile = new File(newPath);
        return this;
    }

    /**
     * path
     *
     * @return - current path.
     */
    public String path() {
        return currentFile.getPath();
    }
}
