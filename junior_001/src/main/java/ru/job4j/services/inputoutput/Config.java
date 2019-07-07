package ru.job4j.services.inputoutput;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Config
 *
 * @author Vladislav Nechaev
 * @since 20.06.2019
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * loadAnswers
     * <p>
     * fills the values from app.properties
     */
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String str = null;
            while ((str = br.readLine()) != null) {
                if (str.contains("=")) {
                    String[] keyValue = str.split("=");
                    values.put(keyValue[0], keyValue[1]);
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * value
     *
     * @param key - the key of values.
     * @return - value of the specified key.
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
