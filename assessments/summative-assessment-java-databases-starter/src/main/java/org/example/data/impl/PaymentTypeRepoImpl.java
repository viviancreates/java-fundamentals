package org.example.data.impl;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.mappers.PaymentTypeMapper;
import org.example.model.PaymentType;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@Repository
public class PaymentTypeRepoImpl implements PaymentTypeRepo {
    @Autowired
    private JdbcTemplate jdbc;
    private PaymentTypeMapper paymentTypeMapper;

    @Autowired
    public PaymentTypeRepoImpl(JdbcTemplate jdbc, PaymentTypeMapper paymentTypeMapper) {
        this.jdbc = jdbc;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    @Override
    public List<PaymentType> getAll() throws InternalErrorException {
        String sql = "SELECT * FROM PaymentType ORDER BY PaymentTypeID";
        try {
            return jdbc.query(sql, paymentTypeMapper);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
