package com.company;

import org.flywaydb.core.Flyway;

import java.util.HashMap;
import java.util.Map;

/**
 * Example application
 */
public class App {
    public static void main(String[] args) {

        // Create the Flyway instance
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);

        String namespace = "10000";
        ConnectionFactory.setupDataSource(flyway, namespace);

        Map<String, String> properties = new HashMap<String, String>();

        properties.put("flyway.driver", "com.mysql.jdbc.Driver");
        properties.put("flyway.url", "jdbc:mysql://localhost:3306/flyway01?autoreconnect=true");
        properties.put("flyway.user", "developer");
        properties.put("flyway.password", "123456");

        flyway.configure(properties);

        // Point it to the database
        flyway.setDataSource("jdbc:mysql://localhost:3306/flyway01?autoreconnect=true", "developer", "123456");

        // Start the migration
        flyway.migrate();
    }
}

