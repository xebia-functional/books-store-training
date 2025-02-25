package com.xebia.register;

import com.xebia.database.DatabaseManager;
import com.xebia.migrations.FlywayMigration;
import com.xebia.models.Register;
import com.xebia.models.User;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.Register.RegisterServiceDBImpl;
import com.xebia.user.TestUserServiceDBSuite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRegisterServiceDBSuite {

    private Logger logger;
    private RegisterService regService;

    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("bookstore_db")
                    .withUsername("xebia_user")
                    .withPassword("xebia_pw");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        FlywayMigration.migrate(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() throws SQLException {
        Connection connection =
                DatabaseManager.getConnection(
                        postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        logger = Logger.getLogger(TestUserServiceDBSuite.class.getName());
        regService = new RegisterServiceDBImpl(logger, connection);
        connection.prepareStatement("TRUNCATE TABLE Register CASCADE").execute();
    }

    @Test
    void addRegisterWhithDateShouldWork(){
        Register newReg = new Register(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        assertTrue(regService.addRegister(newReg.getUserId(), newReg.getBookId(), newReg.getRentDate()));
        assertEquals(true, regService.containsRegister(newReg));
    }
}
