package ru.job4j.services.inputoutput.zip;

import java.io.File;

/**
 * MainZip
 *
 * @author Vladislav Nechaev
 * @since 03.07.2019
 */
public class MainZip {

    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
        Zip zip = new Zip();
        Args arg = new Args(args);
        zip.pack(zip.seekBy(arg.directory(), arg.exclude()),
                    new File(arg.output()));
    }
}
