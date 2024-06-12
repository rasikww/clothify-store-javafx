package edu.icet.coursework.bo.customer.impl;

import edu.icet.coursework.bo.customer.CustomerBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.DAOType;
import org.modelmapper.ModelMapper;

public class CustomerBOImpl implements CustomerBO {
    @Override
    public boolean saveCustomer(Customer customerDTO) {
        CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
        return customerDAO.save(new ModelMapper().map(customerDTO, CustomerEntity.class));
    }

    @Override
    public boolean deleteCustomerById(Integer id) {
        return false;
    }
}
