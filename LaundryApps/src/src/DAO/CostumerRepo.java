package DAO;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Costumer;
import config.Database;

public class CostumerRepo implements CostumerDAO {
    private final Connection connection;

    private static final String INSERT = "INSERT INTO costumer (nama, alamat, no_hp) VALUES (?,?,?)";
    private static final String SELECT = "SELECT * FROM costumer";
    private static final String UPDATE = "UPDATE costumer SET nama=?, alamat=?, no_hp=? WHERE id=?";
    private static final String DELETE = "DELETE FROM costumer WHERE id=?";

    public CostumerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Costumer costumer) {
        try (PreparedStatement st = connection.prepareStatement(INSERT)) {
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNoHp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Costumer> show() {
        List<Costumer> costumerList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT);
            while (rs.next()) {
                Costumer costumer = new Costumer();
                costumer.setId(rs.getString("id"));
                costumer.setNama(rs.getString("nama"));
                costumer.setAlamat(rs.getString("alamat"));
                costumer.setNoHp(rs.getString("no_hp"));
                costumerList.add(costumer);
            }
        } catch (SQLException e) {
            Logger.getLogger(CostumerRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return costumerList;
    }

    @Override
    public void update(Costumer costumer) {
        try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNoHp());
            st.setString(4, costumer.getId());
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
