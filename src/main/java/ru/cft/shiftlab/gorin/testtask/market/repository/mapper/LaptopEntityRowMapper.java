package ru.cft.shiftlab.gorin.testtask.market.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.cft.shiftlab.gorin.testtask.market.model.ProductType;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LaptopEntityRowMapper implements RowMapper<LaptopEntity> {
    @Override
    public LaptopEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        LaptopEntity result = new LaptopEntity();

        result.setId(rs.getLong("id"));
        result.setSerialNumber(rs.getString("serial_number"));
        result.setManufacturer(rs.getString("manufacturer"));
        result.setAmount(rs.getInt("amount"));
        result.setPriceValue(rs.getLong("price_value"));
        result.setProductType(ProductType.LAPTOP);

        result.setSize(rs.getInt("size"));

        return result;
    }
}
