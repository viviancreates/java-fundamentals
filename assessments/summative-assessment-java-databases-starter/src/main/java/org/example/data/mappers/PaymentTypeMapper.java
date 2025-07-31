package org.example.data.mappers;

import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentTypeMapper implements RowMapper<PaymentType> {

    @Override
    public PaymentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentType pt = new PaymentType();
        pt.setPaymentTypeID(rs.getInt("PaymentTypeID"));
        pt.setPaymentTypeName(rs.getString("PaymentTypeName"));
        return pt;
    };
}
