package org.example.data.mappers;

import org.example.model.Tax;
import org.springframework.jdbc.core.RowMapper;

public class TaxMapper {
    public static RowMapper<Tax> map() {
        return (rs, rowNum) -> {
            Tax tax = new Tax();
            tax.setTaxID(rs.getInt("TaxID"));
            tax.setTaxPercentage(rs.getBigDecimal("TaxPercentage"));
            tax.setStartDate(rs.getDate("StartDate").toLocalDate());
            if (rs.getDate("EndDate") != null) {
                tax.setEndDate(rs.getDate("EndDate").toLocalDate());
            }
            return tax;
        };
    }
}