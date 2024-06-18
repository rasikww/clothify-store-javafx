package edu.icet.coursework.bo.customer;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Customer;
import javafx.collections.ObservableList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(Customer customerDTO);
    boolean deleteCustomerById(Integer id);
    Customer getCustomer(Integer customerId);
    boolean updateCustomer(Customer customer);
    ObservableList<Customer> getAllCustomers();
}
