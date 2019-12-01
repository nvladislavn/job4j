package ru.job4j.sql.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreSQL
 *
 * @author Vladislav Nechaev
 * @since 18.09.2019
 */
public class StoreSQL implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        prepareTable("entries");
    }

    /**
     * prepareTable
     *
     * @param tableName - the prepared table.
     */
    private void prepareTable(String tableName) {
        try {
            if (getConnect()) {
                Statement statement = connect.createStatement();
                statement.execute(String.format("CREATE TABLE IF NOT EXISTS %s (field INT);", tableName));
                statement.execute(String.format("DELETE FROM %s", tableName));
                generate(Integer.parseInt(config.get("table-size")), tableName);
                connect.commit();
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * getConnect
     *
     * @return - true if the connection is valid.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private boolean getConnect() throws ClassNotFoundException, SQLException {
        Class.forName(config.get("driver-class-name"));
        connect = DriverManager.getConnection(config.get("url"));
        connect.setAutoCommit(false);
        return connect.isValid(1);
    }

    /**
     * generate
     *
     * @param size      - the number of generated ru.job4j.items.
     * @param tableName -the table name.
     */
    public void generate(int size, String tableName) throws SQLException {
        PreparedStatement statement = connect.prepareStatement(
                String.format("INSERT INTO %s(field) VALUES (?)", tableName)
        );
        for (int i = 1; i <= size; i++) {
            statement.setInt(1, i);
            statement.addBatch();
        }
        statement.executeBatch();
    }

    /**
     * load
     *
     * @return - the list of entries
     */
    public List<Entry> load() {
        List<Entry> entries = new ArrayList<>(Integer.parseInt(config.get("table-size")));
        try {
            ResultSet res = connect.createStatement()
                    .executeQuery(
                            String.format("SELECT * FROM %s;", "entries")
                    );
            while (res.next()) {
                entries.add(
                        new Entry(
                                res.getInt("field")
                        )
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
