package org.example.service.tests;

import org.example.data.ServerRepo;
import org.example.model.Server;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/sql/reset_db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ServerRepoImplTest {

    @Autowired
    private ServerRepo serverRepo;

    @Test
    @DisplayName("Get server by ID and returns server")
    void getServerByIdReturnsServer() throws Exception {
        Server server = serverRepo.getServerById(1);

        assertNotNull(server);
        assertEquals(1, server.getServerID());
    }

    @Test
    @DisplayName("Get server by ID and throws error when not found")
    void getServerByIdThrowsNotFound() {
        assertThrows(RecordNotFoundException.class, () -> serverRepo.getServerById(999));
    }

}

