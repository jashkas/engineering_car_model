package ru.esstu.repository;

import ru.esstu.entity.CarModelEntity;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CarModelRepository {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:./data/dealerDB", "sa", "");
    }

    public void create(CarModelEntity carModel) throws SQLException {
        String sql = "INSERT INTO CarModel (brand, model) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModel());
            stmt.executeUpdate();
            // После создания нужно получить сгенерированный идентификатор
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    carModel.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public CarModelEntity getById(Long id) throws SQLException {
        String sql = "SELECT * FROM CarModel WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CarModelEntity model = new CarModelEntity();
                    model.setId(rs.getLong("id"));
                    model.setBrand(rs.getString("brand"));
                    model.setModel(rs.getString("model"));
                    return model;
                }
            }
        }
        return null;
    }

    public List<CarModelEntity> getAll() throws SQLException {
        List<CarModelEntity> models = new ArrayList<>();
        String sql = "SELECT * FROM CarModel";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CarModelEntity model = new CarModelEntity();
                model.setId(rs.getLong("id"));
                model.setBrand(rs.getString("brand"));
                model.setModel(rs.getString("model"));
                models.add(model);
            }
        }
        return models;
    }

    public void update(CarModelEntity carModel) throws SQLException {
        String sql = "UPDATE CarModel SET brand = ?, model = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModel());
            stmt.setLong(3, carModel.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM CarModel WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
