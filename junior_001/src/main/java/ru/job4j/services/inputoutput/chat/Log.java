package ru.job4j.services.inputoutput.chat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Log
 *
 * @author Vladislav Nechaev
 * @since 06.07.2019
 */
public class Log {

    private String logPath;

    public Log(String logPath) {
        this.logPath = logPath;
        delLastLog();
    }

    /**
     * delLastLog
     *
     * deletes the log file of the last chat.
     */
    private void delLastLog() {
        File check = new File(logPath);
        if (check.exists()) {
            check.delete();
        }
    }

    /**
     * writeToLog
     *
     * @param message - the message to write to the log file.
     */
    public void writeToLog(String message) {
        try (FileWriter out = new FileWriter(new File(logPath), true)) {
            out.write(System.lineSeparator() + message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
