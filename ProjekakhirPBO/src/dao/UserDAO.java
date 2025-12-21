package dao;

import config.DatabaseConfig;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User findByUsername(String username) throws Exception {
        String sql = "SELECT id, username, password_hash, role FROM users WHERE username = ? LIMIT 1";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                return new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"), // ✅ pastikan ini password_hash
                        rs.getString("role")
                );
            }
        }
    }
}
