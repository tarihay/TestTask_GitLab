package ru.cft.shiftlab.gorin.testtask.market.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.cft.shiftlab.gorin.testtask.market.model.ProductType;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PcEntityRowMapper implements RowMapper<PcEntity> {
    @Override
    public PcEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        PcEntity result = new PcEntity();

        result.setId(rs.getLong("id"));
        result.setSerialNumber(rs.getString("serial_number"));
        result.setManufacturer(rs.getString("manufacturer"));
        result.setAmount(rs.getInt("amount"));
        result.setPriceValue(rs.getLong("price_value"));
        result.setProductType(ProductType.PC);

        result.setFormFactor(rs.getString("form_factor"));

        return result;
    }
}
