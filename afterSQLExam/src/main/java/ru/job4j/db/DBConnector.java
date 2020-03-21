package ru.job4j.db;

import ru.job4j.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnector
 *
 * @author Vladislav Nechaev
 * @since 20.03.2020
 */
public class DBConnector {

    private final Config conf;

    public DBConnector(Config conf) {
        this.conf = conf;
    }

    /**
     * getConnectionToServer
     *
     * @return - Instance of connection to the server.
     * @throws SQLException
     */
    public Connection getConnectionToServer() throws SQLException {
        return getConnection(conf.getPropValue("url"));
    }

    public Connection getConnectionToDB() throws SQLException {
        return getConnection(
                String.format(
                            "%s%s",
                            conf.getPropValue("url"),
                            conf.getPropValue("db_name")
                )
        );
    }

    /**
     * getConnection
     *
     * @param url - url of connection
     * @return - Instance of Connection
     * @throws SQLException
     */
    private Connection getConnection(String url) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(
                                                url,
                                                conf.getPropValue("username"),
                                                conf.getPropValue("password"));
        return connection;
    }
}
