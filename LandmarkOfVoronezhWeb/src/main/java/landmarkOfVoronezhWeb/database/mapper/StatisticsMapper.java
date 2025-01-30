package landmarkOfVoronezhWeb.database.mapper;

import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsMapper implements IRowMapper<StatisticsEntity> {
    @Override
    public StatisticsEntity mapRow(ResultSet rs) throws SQLException {
        String name = rs.getString(1);
        int count = rs.getInt(2);

        return new StatisticsEntity(name, count);
    }
}