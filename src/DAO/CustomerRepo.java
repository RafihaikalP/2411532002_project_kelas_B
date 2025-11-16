package DAO;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Customer;
import Model.CustomerBuilder;
import config.Database;

public class CustomerRepo implements CustomerDAO {
    private Connection connection;
    private final String insert = "INSERT INTO customer (nama, email, alamat, hp) VALUES (?,?,?,?);";
    private final String select = "SELECT * FROM customer;";
    private final String delete = "DELETE FROM customer WHERE id=?;";
    private final String update = "UPDATE customer SET nama=?, email=?, alamat=?, hp=? WHERE id=?;";


    public CustomerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Customer cs) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, cs.getNama());
            st.setString(2, cs.getemail());
            st.setString(3, cs.getalamat());
            st.setString(4, cs.gethp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   

    @Override
    public List<Customer> show() {
    	
        List<Customer> ls = null;
        try {
            ls = new ArrayList<Customer>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()) {
                Customer cs = new CustomerBuilder()
                    .setId(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setEmail(rs.getString("email"))
                    .setAlamat(rs.getString("alamat"))
                    .setHp(rs.getString("hp"))
                    .build();
                ls.add(cs);
            }
        } catch (SQLException e) {
           
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }
    @Override
    public void update(Customer customer) {
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, customer.getNama());
            st.setString(2, customer.getemail());
            st.setString(3, customer.getalamat());
            st.setString(4, customer.gethp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
