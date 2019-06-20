package ru.job4j.services.inputoutput;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ConfigTest
 *
 * @author Vladislav Nechaev
 * @since 20.06.2019
 */
public class ConfigTest {

    /**
     * shouldReturnValueOfTheSpecifiedKey
     *
     * tests value()
     */
    @Test
    public void shouldReturnValueOfTheSpecifiedKey() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }
}