package landmarkOfVoronezhWeb.database.repository;

import landmarkOfVoronezhWeb.database.connection.ConnectionManager;
import landmarkOfVoronezhWeb.database.entity.PhotoEntity;
import landmarkOfVoronezhWeb.database.mapper.PhotoMapper;
import landmarkOfVoronezhWeb.database.interfaces.IPhotoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoRepository implements IPhotoRepository {
    private static PhotoRepository instance = null;
    private PhotoRepository() {}
    public static PhotoRepository getInstance() {
        if (instance == null) {
            instance = new PhotoRepository();
        }
        return instance;
    }

    PhotoMapper photoMapper = new PhotoMapper();

    @Override
    public List<PhotoEntity> getPhotosByIdLandmark(int idLandmark) {
        ArrayList<PhotoEntity> list = new ArrayList<>();
        String query = "SELECT * FROM photo WHERE id_landmark = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idLandmark);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PhotoEntity photoEntity = photoMapper.mapRow(resultSet);
                list.add(photoEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Map<Integer, PhotoEntity> getFirstPhotoByIdsLandmarks(List<Integer> idLandmarks) {
        Map<Integer, PhotoEntity> map = new HashMap<>();
        String query = "SELECT * FROM photo WHERE id_landmark = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int idLandmark : idLandmarks) {
                assert preparedStatement != null;
                preparedStatement.setInt(1, idLandmark);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    PhotoEntity photoEntity = photoMapper.mapRow(resultSet);
                    map.put(idLandmark, photoEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
