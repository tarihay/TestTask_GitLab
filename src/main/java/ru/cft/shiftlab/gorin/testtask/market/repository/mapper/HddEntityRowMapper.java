package ru.cft.shiftlab.gorin.testtask.market.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.cft.shiftlab.gorin.testtask.market.model.ProductType;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HddEntityRowMapper implements RowMapper<HddEntity> {

    @Override
    public HddEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        HddEntity result = new HddEntity();

        result.setId(rs.getLong("id"));
        result.setSerialNumber(rs.getString("serial_number"));
        result.setManufacturer(rs.getString("manufacturer"));
        result.setAmount(rs.getInt("amount"));
        result.setPriceValue(rs.getLong("price_value"));
        result.setProductType(ProductType.HDD);

        result.setMemoryVolumeValue(rs.getInt("memory_volume_value"));
        result.setMemoryVolume(rs.getString("memory_volume"));

        return result;
    }
}
