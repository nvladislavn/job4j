package ru.job4j.services.inputoutput;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Analyzer
 *
 * @author Vladislav Nechaev
 * @since 23.06.2019
 */
public class Analyzer {

    /**
     * unavailable
     *
     * @param source - the path to the source file.
     * @param target - the path to the target file.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)))) {
            StringBuilder periods = new StringBuilder();
            boolean usedPeriod = false;
            List<String> lines = in.lines().collect(Collectors.toList());
            for (String line : lines) {
                String[] temp = line.split(" ");
                if (temp[0].equals(MessageType.TYPE400) || temp[0].equals(MessageType.TYPE500)) {
                    if (!usedPeriod) {
                        periods = periods.append(temp[1]);
                        usedPeriod = true;
                    }
                } else {
                    if (usedPeriod) {
                        periods = periods.append(";" + temp[1] + System.lineSeparator());
                        write(periods, target);
                        usedPeriod = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write
     *
     * @param periods - a stringBuilders with unavailable periods.
     * @param path - the path to the target file.
     */
    private void write(StringBuilder periods, String path) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            out.append(periods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MessageType
     */
    private enum MessageType {

        TYPE100("100"),
        TYPE200("200"),
        TYPE300("300"),
        TYPE400("400"),
        TYPE500("500");

        final String values;

        MessageType(String values) {
            this.values = values;
        }
    }
}
