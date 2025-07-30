package org.example.service.tests;

import org.example.data.PaymentTypeRepo;
import org.example.model.PaymentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/sql/reset_db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PaymentTypeRepoImplTest {

    @Autowired
    private PaymentTypeRepo paymentTypeRepo;

    @Test
    @DisplayName("Get all payment types returns list")
    void getAllReturnsList() throws Exception {
        List<PaymentType> types = paymentTypeRepo.getAll();

        assertNotNull(types);
        assertFalse(types.isEmpty());
    }
}