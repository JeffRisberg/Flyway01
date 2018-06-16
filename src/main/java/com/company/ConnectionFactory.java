package com.company;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private final static String DEFAULT_URL = "jdbc:mysql://localhost:3306/flyway01";
    private final static String DEFAULT_BASE_URL = "jdbc:mysql://localhost:3306";
    private final static String DEFAULT_USER = "developer";
    private final static String DEFAULT_SECRET = "123456";

    public static Connection getConnection() throws SQLException {

        String url = DEFAULT_URL;
        String user = DEFAULT_USER;
        String secret = DEFAULT_SECRET;
        return DriverManager.getConnection(url, user, secret);
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultset) {
        try {
            if (resultset != null) resultset.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Flyway needs access to the db
     *
     * @param flyway
     */
    public static void setupDataSource(Flyway flyway, String tenantNamespace) {
        flyway.setDataSource(DEFAULT_BASE_URL + "/" + tenantNamespace,
                DEFAULT_USER,
                DEFAULT_SECRET);
    }
}
