package DAO;
import java.util.List;

import Model.Customer;

public interface CustomerDAO {
    public void save(Customer cs);
    public void update(Customer cs);
    public void delete(String id);
    public List<Customer> show();
}