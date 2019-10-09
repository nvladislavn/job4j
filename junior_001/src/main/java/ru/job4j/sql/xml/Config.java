package ru.job4j.sql.xml;

import java.io.InputStream;
import java.util.Properties;

/**
 * Config
 *
 * @author Vladislav Nechaev
 * @since 18.09.2019
 */

public class Config {

    private final Properties properties = new Properties();

    public Config() {
        init();
    }

    /**
     * init
     * loads the properties
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.properties.getProperty(key);
    }
}
