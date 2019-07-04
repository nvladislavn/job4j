package ru.job4j.services.inputoutput.zip;

/**
 * Args
 *
 * @author Vladislav Nechaev
 * @since 03.07.2019
 */
public class Args {

    private String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    /**
     * directory
     *
     * @return - path to archived directory.
     */
    public String directory() {
        return args[1];
    }

    /**
     * exclude
     *
     * @return - list of excluded extensions.
     */
    public String exclude() {
        return args[3];
    }

    /**
     * output
     *
     * @return - path to the target archive file.
     */
    public String output() {
        return args[5];
    }
}
