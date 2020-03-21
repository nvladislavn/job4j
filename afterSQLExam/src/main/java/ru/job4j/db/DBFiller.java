package ru.job4j.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBFiller
 *
 * @author Vladislav Nechaev
 * @since 20.03.2020
 */
public class DBFiller {

    /**
     * The SQL string to fill the meeting table
     */
    private static final String FILL_MEETINGS = "INSERT INTO meetings (name) "
                                                + "VALUES ('meeting_1'), "
                                                        + "('meeting_2'), "
                                                        + "('meeting_3');";
    /**
     * The SQL string to fill the user table
     */
    private static final String FILL_USERS = "INSERT INTO users (name) "
                                                + "VALUES ('John'), "
                                                        + "('Mike'), "
                                                        + "('Katy'), "
                                                        + "('Jane');";
    /**
     * The SQL string to fill the meetings_users table
     */
    private static final String FILL_MEETINGS_USERS = "INSERT INTO meetings_users "
                                                        + "VALUES (1, 1, true), "
                                                                + "(2, 2, true), "
                                                                + "(2, 1, false), "
                                                                + "(2, 3, true), "
                                                                + "(2, 4, true);";
    private final Connection connection;

    public DBFiller(Connection connection) {
        this.connection = connection;
    }

    /**
     * fillTables
     *
     * @throws SQLException
     */
    public void fillTables() throws SQLException {
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        statement.addBatch(FILL_MEETINGS);
        statement.addBatch(FILL_USERS);
        statement.addBatch(FILL_MEETINGS_USERS);
        statement.executeBatch();
        connection.commit();
    }
}
