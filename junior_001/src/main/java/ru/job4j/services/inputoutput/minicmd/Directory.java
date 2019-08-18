package ru.job4j.services.inputoutput.minicmd;

/**
 * Directory
 *
 * @author Vladislav Nechaev
 * @since 22.08.2019
 */
public class Directory {

    public static void main(String[] args) throws IllegalCommandException {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        String startDirectory = args[0];
        final Shell shell = new Shell(args[0]);
        assert shell.path().equalsIgnoreCase(startDirectory);
        shell.cd("cd");
        assert shell.path().equalsIgnoreCase(startDirectory);
        shell.cd("cd..");
        assert shell.path().equalsIgnoreCase("C:\\projects\\job4j");
        shell.cd("cd junior_001");
        assert shell.path().equalsIgnoreCase("C:\\projects\\job4j\\junior_001");
        shell.cd("cd /D D:\\123");
        shell.path().equalsIgnoreCase("D:\\123");
        shell.cd("cd 111").cd("cd /D C:\\projects").cd("cd job4j").cd("cd..");
        shell.path().equalsIgnoreCase("C:\\projects");
    }
}
