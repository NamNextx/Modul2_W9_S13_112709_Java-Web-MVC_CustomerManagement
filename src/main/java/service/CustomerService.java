package service;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer fineById(int id);
    void update(int id,Customer customer);
    void remove(int id);
}
