package org.example.data.mappers;

import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;

public class PaymentTypeMapper {
    public static RowMapper<PaymentType> map() {
        return (rs, rowNum) -> {
            PaymentType pt = new PaymentType();
            pt.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            pt.setPaymentTypeName(rs.getString("PaymentTypeName"));
            return pt;
        };
    }
}