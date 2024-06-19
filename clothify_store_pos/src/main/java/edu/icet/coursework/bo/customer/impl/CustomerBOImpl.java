package edu.icet.coursework.bo.customer.impl;

import edu.icet.coursework.bo.customer.CustomerBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    @Override
    public boolean saveCustomer(Customer customerDTO) {
        return customerDAO.save(customerDTO);
    }

    @Override
    public boolean deleteCustomerById(Integer customerId) {
        return customerDAO.deleteById(customerId);
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        try {
            return new ModelMapper().map(customerDAO.getById(customerId),Customer.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
}
