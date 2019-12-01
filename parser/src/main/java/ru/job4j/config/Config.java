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
 * @since 19.11.2019
 */
public class Config {

    private final Properties properties = new Properties();
    private static final Logger LOG = LogManager.getLogger(Config.class.getName());

    public Config() {
        loadProperty();
    }

    private void loadProperty() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
