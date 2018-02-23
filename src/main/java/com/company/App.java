package com.company;

import org.flywaydb.core.Flyway;

/**
 * Example application
 */
public class App {
    public static void main(String[] args) {

        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:h2:file:./target/foobar", "sa", null);

        // Start the migration
        flyway.migrate();
    }
}

