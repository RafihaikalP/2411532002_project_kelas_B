package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void addCustomer(Customer c) throws Exception {
        customerDAO.create(c);
    }

    public List<Customer> getAll() throws Exception {
        return customerDAO.findAll();
    }

    public Customer getById(String id) throws Exception {
        return customerDAO.findById(id);
    }

    public void update(Customer c) throws Exception {
        customerDAO.update(c);
    }

    public void delete(String id) throws Exception {
        customerDAO.delete(id);
    }
}
