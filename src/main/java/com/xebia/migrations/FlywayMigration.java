package com.xebia.migrations;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;

public class FlywayMigration {
  public static void main(String[] args) {}

  public static void migrate(PostgreSQLContainer<?> postgres) {
    Flyway flyway =
        Flyway.configure()
            .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
            .locations("classpath:db/migrations")
            .load();

    flyway.migrate();
  }
}
