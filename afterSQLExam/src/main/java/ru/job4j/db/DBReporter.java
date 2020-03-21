package ru.job4j.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * DBExec
 *
 * @author Vladislav Nechaev
 * @since 20.03.2020
 */
public class DBReporter {

    /**
     * The SQL string to create the view for get confirmed applications.
     */
    private static final String CREATE_VIEW_GET_APP = "DROP VIEW IF EXISTS confirmed_app; "
                                                        + "CREATE VIEW confirmed_app as "
                                                        + "SELECT meeting_id, count(*) as count "
                                                        + "FROM meetings_users "
                                                        + "WHERE user_status = true "
                                                        + "GROUP BY meeting_id;";
    /**
     * The SQL string for get meetings with the number of confirmed members.
     */
    private static final String GET_CONFIRM_APP_NUMBER = "SELECT m.name meeting_name, COALESCE(ca.count, 0) app_number "
                                                            + "FROM meetings m LEFT JOIN confirmed_app ca "
                                                            + "ON m.id = ca.meeting_id";
    /**
     * The SQL string for get meetings without applications.
     */
    private static final String GET_EMPTY_MEETINGS = "SELECT m.name meeting_name "
                                                        + "FROM meetings m LEFT JOIN confirmed_app ca "
                                                        + "ON m.id = ca.meeting_id "
                                                        + "WHERE ca.meeting_id ISNULL";

    private final Connection connection;

    public DBReporter(Connection connection) {
        this.connection = connection;
    }

    /**
     * getEmptyMeetings
     *
     * @return - list of meetings without applications.
     * @throws SQLException
     */
    public List<String> getEmptyMeetings() throws SQLException {
        List<String> emptyMeetings = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_EMPTY_MEETINGS);
        while (resultSet.next()) {
            emptyMeetings.add(resultSet.getString("meeting_name"));
        }
        return emptyMeetings;
    }

    /**
     * getConfirmAppNumber
     *
     * @return - the Map of meetings with the number of confirmed members
     * @throws SQLException
     */
    public Map<String, String> getConfirmAppNumber() throws SQLException {
        Map<String, String> res = new TreeMap<>();
        if (connection != null) {
            createView(CREATE_VIEW_GET_APP);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CONFIRM_APP_NUMBER);
            while (resultSet.next()) {
                res.put(resultSet.getString("meeting_name"), resultSet.getString("app_number"));
            }
        }
        return res;
    }

    /**
     * createView
     *
     * @param sql - the SQL string to create the view
     * @throws SQLException
     */
    private void createView(String sql) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }
}
