package ru.cft.shiftlab.gorin.testtask.market.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.cft.shiftlab.gorin.testtask.market.model.ProductType;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MonitorEntityRowMapper implements RowMapper<MonitorEntity> {
    @Override
    public MonitorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonitorEntity result = new MonitorEntity();

        result.setId(rs.getLong("id"));
        result.setSerialNumber(rs.getString("serial_number"));
        result.setManufacturer(rs.getString("manufacturer"));
        result.setAmount(rs.getInt("amount"));
        result.setPriceValue(rs.getLong("price_value"));
        result.setProductType(ProductType.MONITOR);

        result.setDiagonalSize(rs.getInt("diagonal_size"));

        return result;
    }
}
