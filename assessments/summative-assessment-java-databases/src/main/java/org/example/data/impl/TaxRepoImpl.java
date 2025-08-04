package org.example.data.impl;

import org.example.data.TaxRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Tax;
import org.springframework.stereotype.Repository;
import org.example.data.mappers.TaxMapper;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

@Repository
public class TaxRepoImpl implements TaxRepo {
    @Autowired
    private JdbcTemplate jdbc;
    private TaxMapper taxMapper;

    public TaxRepoImpl(JdbcTemplate jdbc, TaxMapper taxMapper) {
        this.jdbc = jdbc;
        this.taxMapper = taxMapper;
    }

    @Override
    public Tax getCurrentTax(LocalDate dateOf) throws InternalErrorException, RecordNotFoundException {
        String sql = "SELECT * FROM Tax WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)";

        try {
            return jdbc.queryForObject(sql, taxMapper, dateOf, dateOf);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new RecordNotFoundException();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}

