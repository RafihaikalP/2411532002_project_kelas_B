package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConfig {
    private static DatabaseConfig instance;
    private final String url;
    private final String user;
    private final String pass;

    private DatabaseConfig() {
    	this.url = "jdbc:mysql://localhost:3306/ps_rental?useSSL=false&serverTimezone=UTC";
    	this.user = "root";
    	this.pass = "";
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) instance = new DatabaseConfig();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
