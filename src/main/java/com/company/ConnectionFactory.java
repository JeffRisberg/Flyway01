package com.company;

import org.flywaydb.core.Flyway;

import java.sql.*;

public class ConnectionFactory {

    private final static String TENANTSTORE_DRIVER = "aisera.framework.tenantstore.driver";
    private final static String TENANTSTORE_URL = "aisera.framework.tenantstore.url";
    private final static String TENANTSTORE_USER = "aisera.framework.tenantstore.user";
    private final static String TENANTSTORE_SECRET = "aisera.framework.tenantstore.secret";

    private final static String DEFAULT_URL = "jdbc:mariadb://localhost:3306/tenant_store";
    private final static String DEFAULT_BASE_URL = "jdbc:mariadb://localhost:3306";
    private final static String DEFAULT_USER = "aiserauser";
    private final static String DEFAULT_SECRET = "aiserapassword";

    public static Connection getConnection() throws SQLException {

        ResourceLocator.registerProperties(
                ConnectionFactory.class.getResourceAsStream("/config/aisera_tenantstore.properties"));

        String url = ResourceLocator.getResource(TENANTSTORE_URL).orElse(DEFAULT_URL);
        String user = ResourceLocator.getResource(TENANTSTORE_USER).orElse(DEFAULT_USER);
        String secret = ResourceLocator.getResource(TENANTSTORE_SECRET).orElse(DEFAULT_SECRET);
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
     * @param flyway
     */
    public static void setupDataSource(Flyway flyway, String tenantNamespace) {
        flyway.setDataSource(DEFAULT_BASE_URL + "/" + tenantNamespace,
                ResourceLocator.getResource(TENANTSTORE_USER).orElse(DEFAULT_USER),
                ResourceLocator.getResource(TENANTSTORE_SECRET).orElse(DEFAULT_SECRET));
    }
}
