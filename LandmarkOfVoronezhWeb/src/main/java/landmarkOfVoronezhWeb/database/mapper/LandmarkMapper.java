package landmarkOfVoronezhWeb.database.mapper;

import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandmarkMapper implements IRowMapper<LandmarkEntity> {
    @Override
    public LandmarkEntity mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String history = rs.getString("history");
        int countVisits = rs.getInt("count_visits");
        int category = rs.getInt("category");

        return new LandmarkEntity(id, name, description, history, countVisits, category);
    }
}