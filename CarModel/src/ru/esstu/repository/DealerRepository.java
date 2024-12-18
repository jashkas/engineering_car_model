package ru.esstu.repository;

import ru.esstu.entity.DealerEntity;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DealerRepository {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:./data/dealerDB", "sa", "");
    }

    public void create(DealerEntity dealer) throws SQLException {
        String sql = "INSERT INTO Dealer (name) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealer.getName());
            stmt.executeUpdate();
        }
    }

    public DealerEntity getById(Long id) throws SQLException {
        String sql = "SELECT * FROM Dealer WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DealerEntity dealer = new DealerEntity();
                    dealer.setId(rs.getLong("id"));
                    dealer.setName(rs.getString("name"));
                    return dealer;
                }
            }
        }
        return null;
    }

    public List<DealerEntity> getAll() throws SQLException {
        List<DealerEntity> dealers = new ArrayList<>();
        String sql = "SELECT * FROM Dealer";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DealerEntity dealer = new DealerEntity();
                dealer.setId(rs.getLong("id"));
                dealer.setName(rs.getString("name"));
                dealers.add(dealer);
            }
        }
        return dealers;
    }

    public void update(DealerEntity dealer) throws SQLException {
        String sql = "UPDATE Dealer SET name = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealer.getName());
            stmt.setLong(2, dealer.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Dealer WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
