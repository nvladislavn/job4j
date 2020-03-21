package ru.job4j.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBPrepare
 *
 * @author Vladislav Nechaev
 * @since 20.03.2020
 */
public class DBDescriptor {

    /**
     * The SQL string to create the DB
     */
    private static final String CREATE_DB = "DROP DATABASE IF EXISTS job4j_sql_db;"
                                            + " CREATE DATABASE job4j_sql_db;";
    /**
     * The SQL string to create meeting table
     */
    private static final String CREATE_MEETINGS_TABLE = "CREATE TABLE IF NOT EXISTS meetings ("
                                                        + "id SERIAL PRIMARY KEY, "
                                                        + "name varchar(250) NOT NULL"
                                                        + ");";
    /**
     * The SQL string to create user table
     */
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users ("
                                                        + "id SERIAL PRIMARY KEY, "
                                                        + "name varchar(250) NOT NULL"
                                                        + ");";
    /**
     * The SQL string to create meetings_users table
     */
    private static final String CREATE_MEETINGS_USERS_TABLE = "CREATE TABLE IF NOT EXISTS meetings_users ("
                                                                + "meeting_id int REFERENCES meetings(id), "
                                                                + "user_id int REFERENCES users(id), "
                                                                + "user_status boolean DEFAULT false, "
                                                                + "PRIMARY KEY (meeting_id, user_id)"
                                                                + ");";

    /**
     * createTables
     *
     * @param connection - instance of connection to the DB
     * @throws SQLException
     */
    public void createTables(Connection connection) throws SQLException {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.addBatch(CREATE_MEETINGS_TABLE);
            statement.addBatch(CREATE_USERS_TABLE);
            statement.addBatch(CREATE_MEETINGS_USERS_TABLE);
            statement.executeBatch();
            connection.commit();
    }

    /**
     * createDB
     *
     * @param connection - instance of connection to the server
     * @throws SQLException
     */
    public void createDB(Connection connection) throws SQLException {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_DB);
    }
}
