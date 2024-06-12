package edu.icet.coursework.bo.customer;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Customer;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(Customer customerDTO);
    boolean deleteCustomerById(Integer id);
}