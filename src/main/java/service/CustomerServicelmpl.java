package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServicelmpl implements CustomerService{
    private static Map<Integer, Customer> customerMap;
    static {
        customerMap=new HashMap<>();
        customerMap.put(1,new Customer(1, "John",  "john@codegym.vn", "Hanoi"));
        customerMap.put(2,new Customer(2, "John1", "1john@codegym.vn", "Hanoi"));
        customerMap.put(3,new Customer(3, "John2", "2john@codegym.vn", "Hanoi"));
        customerMap.put(4,new Customer(4, "John3", "3john@codegym.vn", "Hanoi"));
        customerMap.put(5,new Customer(5, "John4", "4john@codegym.vn", "Hanoi"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public void save(Customer customer) {
        customerMap.put(customer.getId(),customer);
    }

    @Override
    public Customer fineById(int id) {
        return customerMap.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customerMap.put(id,customer);
    }

    @Override
    public void remove(int id) {
        customerMap.remove(id);
    }
}
