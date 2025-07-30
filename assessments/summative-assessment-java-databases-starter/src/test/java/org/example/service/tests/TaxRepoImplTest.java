package org.example.service.tests;

import org.example.data.TaxRepo;
import org.example.model.Tax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/sql/reset_db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TaxRepoImplTest {
    @Autowired
    private TaxRepo taxRepo;

    @Test
    @DisplayName("Get current tax by valid date")
    void getCurrentTaxReturnsRecord() throws Exception {
        LocalDate date = LocalDate.of(2024, 1, 15); // adjust as needed to match your seed data
        Tax result = taxRepo.getCurrentTax(date);

        assertNotNull(result);
        assertTrue(result.getStartDate().compareTo(date) <= 0);
        assertTrue(result.getEndDate() == null || result.getEndDate().compareTo(date) >= 0);
    }

}