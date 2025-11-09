package config;
import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
	Connection conn;
	public static Connection koneksi() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/laundry_apps", "root", "");
			System.out.println("anda berhasil login");
			return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	public static void main(String args[]) {
		Database.koneksi();
	}

}