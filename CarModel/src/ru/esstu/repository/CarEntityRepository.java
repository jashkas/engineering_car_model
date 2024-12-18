package ru.esstu.repository;

import ru.esstu.entity.CarEntity;
import ru.esstu.entity.CarModelEntity;
import ru.esstu.entity.DealerEntity;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CarEntityRepository {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:./data/dealerDB", "sa", "");
    }

    public void create(CarEntity car) throws SQLException {
        String sql = "INSERT INTO Car (model_id, dealer_id, country_origin, country_code, status, configuration, color, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, car.getCarModel().getId());
            stmt.setLong(2, car.getDealer().getId());
            stmt.setString(3, car.getCountryOrigin());
            stmt.setString(4, car.getCountryCode());
            stmt.setString(5, car.getStatus());
            stmt.setString(6, car.getConfiguration());
            stmt.setString(7, car.getColor());
            stmt.setDouble(8, car.getPrice());
            stmt.executeUpdate();
            // После создания нужно получить сгенерированный идентификатор
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public CarEntity getById(Long id) throws SQLException {
        String sql = "SELECT c.*, cm.brand, cm.model, d.name FROM Car c JOIN CarModel cm ON c.model_id = cm.id JOIN Dealer d ON c.dealer_id = d.id WHERE c.id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CarEntity car = new CarEntity();
                    car.setId(rs.getLong("id"));
                    CarModelEntity carModel = new CarModelEntity();
                    carModel.setId(rs.getLong("model_id"));
                    carModel.setBrand(rs.getString("brand"));
                    carModel.setModel(rs.getString("model"));
                    car.setCarModel(carModel);

                    DealerEntity dealer = new DealerEntity();
                    dealer.setId(rs.getLong("dealer_id"));
                    dealer.setName(rs.getString("name"));
                    car.setDealer(dealer);

                    car.setCountryOrigin(rs.getString("country_origin"));
                    car.setCountryCode(rs.getString("country_code"));
                    car.setStatus(rs.getString("status"));
                    car.setConfiguration(rs.getString("configuration"));
                    car.setColor(rs.getString("color"));
                    car.setPrice(rs.getDouble("price"));
                    return car;
                }
            }
        }
        return null;
    }

    public List<CarEntity> getAll() throws SQLException {
        List<CarEntity> cars = new ArrayList<>();
        String sql = "SELECT c.*, cm.brand, cm.model, d.name FROM Car c JOIN CarModel cm ON c.model_id = cm.id JOIN Dealer d ON c.dealer_id = d.id";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CarEntity car = new CarEntity();
                car.setId(rs.getLong("id"));

                CarModelEntity carModel = new CarModelEntity();
                carModel.setId(rs.getLong("model_id"));
                carModel.setBrand(rs.getString("brand"));
                carModel.setModel(rs.getString("model"));
                car.setCarModel(carModel);

                DealerEntity dealer = new DealerEntity();
                dealer.setId(rs.getLong("dealer_id"));
                dealer.setName(rs.getString("name"));
                car.setDealer(dealer);

                car.setCountryOrigin(rs.getString("country_origin"));
                car.setCountryCode(rs.getString("country_code"));
                car.setStatus(rs.getString("status"));
                car.setConfiguration(rs.getString("configuration"));
                car.setColor(rs.getString("color"));
                car.setPrice(rs.getDouble("price"));

                cars.add(car);
            }
        }
        return cars;
    }

    public void update(CarEntity car) throws SQLException {
        String sql = "UPDATE Car SET model_id = ?, dealer_id = ?, country_origin = ?, country_code = ?, status = ?, configuration = ?, color = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, car.getCarModel().getId());
            stmt.setLong(2, car.getDealer().getId());
            stmt.setString(3, car.getCountryOrigin());
            stmt.setString(4, car.getCountryCode());
            stmt.setString(5, car.getStatus());
            stmt.setString(6, car.getConfiguration());
            stmt.setString(7, car.getColor());
            stmt.setDouble(8, car.getPrice());
            stmt.setLong(9, car.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Car WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
