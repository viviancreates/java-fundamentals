package org.example.data.impl;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.model.PaymentType;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PaymentTypeRepoImpl implements PaymentTypeRepo {

    @Override
    public List<PaymentType> getAll() throws InternalErrorException {
        return Collections.emptyList();
    }
}
