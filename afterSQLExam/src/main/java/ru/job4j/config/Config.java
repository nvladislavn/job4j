package ru.job4j.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config
 *
 * @author Vladislav Nechaev
 * @since 19.03.2020
 */
public class Config {

    private final Properties properties = new Properties();
    private static final Logger LOGGER = LogManager.getLogger(Config.class.getName());

    public Config() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public String getPropValue(String key) {
        return properties.getProperty(key);
    }
}
