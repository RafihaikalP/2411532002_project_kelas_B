package dao;

import config.DatabaseConfig;
import model.PlayStation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayStationDAO implements CrudDAO<PlayStation> {

    @Override
    public void create(PlayStation psObj) throws Exception {
        String sql = "INSERT INTO playstations (id, type, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, psObj.getId());
            ps.setString(2, psObj.getType());
            ps.setString(3, psObj.getStatus());
            ps.executeUpdate();
        }
    }

    @Override
    public PlayStation findById(String id) throws Exception {
        String sql = "SELECT * FROM playstations WHERE id = ?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new PlayStation(
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getString("status")
                );
            }
        }
    }

    @Override
    public List<PlayStation> findAll() throws Exception {
        String sql = "SELECT * FROM playstations ORDER BY type ASC";
        List<PlayStation> list = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new PlayStation(
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getString("status")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(PlayStation psObj) throws Exception {
        String sql = "UPDATE playstations SET type=?, status=? WHERE id=?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, psObj.getType());
            ps.setString(2, psObj.getStatus());
            ps.setString(3, psObj.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws Exception {
        String sql = "DELETE FROM playstations WHERE id=?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    public List<PlayStation> findAvailable() throws Exception {
        String sql = "SELECT * FROM playstations WHERE status='available' ORDER BY type ASC";
        List<PlayStation> list = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new PlayStation(
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getString("status")
                ));
            }
        }
        return list;
    }
}
