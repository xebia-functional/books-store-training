package com.xebia.migrations;

import com.xebia.database.DatabaseManager;
import org.flywaydb.core.Flyway;

public class FlywayMigration {
  public static void main(String[] args) {
    Flyway flyway =
        Flyway.configure()
            .dataSource(DatabaseManager.URL, DatabaseManager.USER, DatabaseManager.PASSWORD)
            .locations("classpath:db/migrations")
            .load();

    flyway.migrate();
  }
}
