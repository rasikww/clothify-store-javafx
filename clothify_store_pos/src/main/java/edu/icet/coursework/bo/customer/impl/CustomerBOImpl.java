package edu.icet.coursework.bo.customer.impl;

import edu.icet.coursework.bo.customer.CustomerBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    @Override
    public boolean saveCustomer(Customer customerDTO) {
        return customerDAO.save(new ModelMapper().map(customerDTO, CustomerEntity.class));
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
        return customerDAO.update(new ModelMapper().map(customer, CustomerEntity.class));
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
}
