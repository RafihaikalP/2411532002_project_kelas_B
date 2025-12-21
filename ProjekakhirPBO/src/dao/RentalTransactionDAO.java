package dao;

import config.DatabaseConfig;
import model.Customer;
import model.PlayStation;
import model.RentalTransaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalTransactionDAO implements CrudDAO<RentalTransaction> {

    @Override
    public void create(RentalTransaction t) throws Exception {
        String sql = "INSERT INTO rental_transactions (id, customer_id, ps_id, start_time, end_time, cost) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getId());
            ps.setString(2, t.getCustomer().getId());
            ps.setString(3, t.getPlayStation().getId());
            ps.setTimestamp(4, Timestamp.valueOf(t.getStartTime()));
            if (t.getEndTime() == null) ps.setNull(5, Types.TIMESTAMP);
            else ps.setTimestamp(5, Timestamp.valueOf(t.getEndTime()));
            ps.setDouble(6, t.getCost());
            ps.executeUpdate();
        }
    }

    @Override
    public RentalTransaction findById(String id) throws Exception {
        String sql = "SELECT rt.*, c.name, c.phone, c.address, ps.type, ps.status " +
                     "FROM rental_transactions rt " +
                     "JOIN customers c ON rt.customer_id=c.id " +
                     "JOIN playstations ps ON rt.ps_id=ps.id " +
                     "WHERE rt.id=?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Customer c = new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
                PlayStation p = new PlayStation(
                        rs.getString("ps_id"),
                        rs.getString("type"),
                        rs.getString("status")
                );

                Timestamp endTs = rs.getTimestamp("end_time");
                LocalDateTime end = (endTs == null) ? null : endTs.toLocalDateTime();

                return new RentalTransaction(
                        rs.getString("id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        end,
                        rs.getDouble("cost"),
                        c,
                        p
                );
            }
        }
    }

    @Override
    public List<RentalTransaction> findAll() throws Exception {
        String sql = "SELECT rt.*, c.name, c.phone, c.address, ps.type, ps.status " +
                     "FROM rental_transactions rt " +
                     "JOIN customers c ON rt.customer_id=c.id " +
                     "JOIN playstations ps ON rt.ps_id=ps.id " +
                     "ORDER BY rt.start_time DESC";

        List<RentalTransaction> list = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
                PlayStation p = new PlayStation(
                        rs.getString("ps_id"),
                        rs.getString("type"),
                        rs.getString("status")
                );
                Timestamp endTs = rs.getTimestamp("end_time");
                LocalDateTime end = (endTs == null) ? null : endTs.toLocalDateTime();

                list.add(new RentalTransaction(
                        rs.getString("id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        end,
                        rs.getDouble("cost"),
                        c,
                        p
                ));
            }
        }
        return list;
    }

    @Override
    public void update(RentalTransaction t) throws Exception {
        String sql = "UPDATE rental_transactions SET end_time=?, cost=? WHERE id=?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (t.getEndTime() == null) ps.setNull(1, Types.TIMESTAMP);
            else ps.setTimestamp(1, Timestamp.valueOf(t.getEndTime()));
            ps.setDouble(2, t.getCost());
            ps.setString(3, t.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws Exception {
        String sql = "DELETE FROM rental_transactions WHERE id=?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
}
