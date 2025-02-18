package com.xebia;

import com.xebia.models.Register;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.Register.RegisterServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegisterServiceSuite {

    private List<Register> registers;
    private Logger logger;
    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        logger = Logger.getLogger(TestUserServiceSuite.class.getName());
        registerService = new RegisterServiceImp(logger);
    }

    @Test
    public void addRegisterShouldWork(){
        // Given
        // When
        boolean result = registerService.addRegister(UUID.randomUUID(),UUID.randomUUID(), LocalDate.now());

        // Expected
        assertTrue(result);
    }

    @Test
    public  void  addRegisterShouldWorkWhenDuplicate(){
        // Given
        UUID duplicateBook = UUID.randomUUID();
        UUID duplicateUser = UUID.randomUUID();
        registerService.addRegister(duplicateUser,duplicateBook, LocalDate.now());

        //When
        boolean result = registerService.addRegister(duplicateUser,duplicateBook, LocalDate.now());

        // Expected
        assertFalse(result);
    }

    @Test
    public  void  removeRegisterShouldWork(){
        // Given
        UUID sampleBook = UUID.randomUUID();
        UUID sampleUser = UUID.randomUUID();
        registerService.addRegister(sampleUser, sampleBook, LocalDate.now());

        //When
        boolean result = registerService.removeRegister(sampleUser,sampleBook, LocalDate.now());

        // Expected
        assertTrue(result);
    }

    @Test
    public  void  removeRegisterShouldWorkWhenNotFound(){
        // Given
        //When
        boolean result = registerService.removeRegister(UUID.randomUUID(),UUID.randomUUID(), LocalDate.now());


        // Expected
        assertFalse(result);
    }

    @Test
    public void searchRegisterByUserShouldWork(){
        // Given
        UUID sampleUser = UUID.randomUUID();
        registerService.addRegister(sampleUser, UUID.randomUUID(), LocalDate.now());

        // When
        List<Register> registerSearch = registerService.searchRegisterByUser(sampleUser);

        // Expected
        assertFalse(registerSearch.isEmpty());
    }

    @Test
    public void searchRegisterByUserShouldWorkWhenNotFound(){
        // Given
        UUID sampleUser = UUID.randomUUID();
        registerService.addRegister(sampleUser, UUID.randomUUID(), LocalDate.now());

        // When
        List<Register> registerSearch = registerService.searchRegisterByUser(UUID.randomUUID());

        // Expected
        assertTrue(registerSearch.isEmpty());
    }

    @Test
    public void searchRegisterByBookShouldWork(){
        // Given
        UUID sampleBook = UUID.randomUUID();
        registerService.addRegister(UUID.randomUUID(), sampleBook, LocalDate.now());

        // When
        List<Register> registerSearch = registerService.searchRegisterByBook(sampleBook);

        // Expected
        assertFalse(registerSearch.isEmpty());
    }

    @Test
    public void searchRegisterByBookShouldWorkWhenNotFound(){
        // Given
        UUID sampleBook = UUID.randomUUID();
        registerService.addRegister(UUID.randomUUID(), sampleBook, LocalDate.now());

        // When
        List<Register> registerSearch = registerService.searchRegisterByBook(UUID.randomUUID());

        // Expected
        assertTrue(registerSearch.isEmpty());
    }

    @Test
    public void listRegisteredShouldWork(){
        // Given
        registerService.addRegister(UUID.randomUUID(),UUID.randomUUID(),LocalDate.now());
        registerService.addRegister(UUID.randomUUID(),UUID.randomUUID(),LocalDate.now());
        registerService.addRegister(UUID.randomUUID(),UUID.randomUUID(),LocalDate.now());

        //When
        registerService.listRegistered();

        // Expected
        assertEquals(3, registerService.listRegistered().size());
    }

    @Test
    public void listRegisteredShouldWorkWhenEmpty(){
        // Given

        //When
        registerService.listRegistered();

        // Expected
        assertEquals(0, registerService.listRegistered().size());
    }
}
