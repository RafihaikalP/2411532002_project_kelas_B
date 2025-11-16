package DAO;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Service;
import config.Database;

public class ServiceRepo implements ServiceDAO {
    private final Connection connection;

    private static final String INSERT = "INSERT INTO service (nama, harga) VALUES (?,?)";
    private static final String SELECT = "SELECT * FROM service";
    private static final String UPDATE = "UPDATE service SET nama=?, harga=? WHERE id=?";
    private static final String DELETE = "DELETE FROM service WHERE id=?";

    public ServiceRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Service service) {
        try (PreparedStatement st = connection.prepareStatement(INSERT)) {
            st.setString(1, service.getNama());
            st.setDouble(2, service.getHarga());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Service> show() {
        List<Service> serviceList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT);
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setNama(rs.getString("nama"));
                service.setHarga(rs.getDouble("harga"));
                serviceList.add(service);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return serviceList;
    }

    @Override
    public void update(Service service) {
        try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(1, service.getNama());
            st.setDouble(2, service.getHarga());
            st.setString(3, service.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement(DELETE)) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
