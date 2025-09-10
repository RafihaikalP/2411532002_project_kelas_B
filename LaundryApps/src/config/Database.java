package config;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Database {
    Connection conn;

    public static Connection koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/laundry_apps", "root", "");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public class Test {
    	public static void main(String[] args) {
            Connection conn = Database.koneksi();
            if (conn != null) {
                JOptionPane.showMessageDialog(null, "Koneksi ke database berhasil!");
            } else {
                JOptionPane.showMessageDialog(null, "Koneksi ke database gagal!");
            }
        }

    }
}

