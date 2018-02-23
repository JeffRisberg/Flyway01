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

        String tenantNamespace = "10000";
        ConnectionFactory.setupDataSource(flyway, tenantNamespace);

        Map<String, String> properties = new HashMap<String, String>();

        properties.put("flyway.driver", "com.mysql.jdbc.Driver");
        properties.put("flyway.url", "jdbc:mysql://localhost:3306/flyway01?autoreconnect=true");
        properties.put("flyway.user", "developer");
        properties.put("flyway.password", "123456");

        flyway.configure(properties);

        // Point it to the database
        //flyway.setDataSource("jdbc:h2:file:./target/foobar", "sa", null);

        // Start the migration
        flyway.migrate();
    }
}

