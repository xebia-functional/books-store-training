package com.xebia.migrations;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
  public static void main(String[] args) {}

  public static void migrate(String url, String username, String password) {
    Flyway flyway =
        Flyway.configure()
            .dataSource(url, username, password)
            .locations("classpath:db/migrations")
            .load();

    flyway.migrate();
  }
}
