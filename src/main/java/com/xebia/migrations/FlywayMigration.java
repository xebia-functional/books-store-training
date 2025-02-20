package com.xebia.migrations;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
  public static void main(String[] args) {
    Flyway flyway =
        Flyway.configure()
            .dataSource("jdbc:postgresql://localhost:5432/bookstore_db", "xebia_user", "xebia_pw")
            .locations("classpath:db/migrations")
            .load();

    flyway.migrate();
  }
}
