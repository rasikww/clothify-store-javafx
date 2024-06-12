package edu.icet.coursework.controller.customer;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.customer.CustomerBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.BOType;
import edu.icet.coursework.util.DAOType;

public class CustomerController {
    private static CustomerController instance;
    private CustomerController(){}
    public static CustomerController getInstance(){
        if (instance == null) {
            return instance = new CustomerController();
        }
        return instance;
    }
    //--------------------------------------
    public String generateNextCustomerId() {
        CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
        CustomerEntity lastCustomer = customerDAO.getLast();
        return String.valueOf((lastCustomer.getCustomerId()+1));
    }

    public boolean addCustomer(Customer newCustomer) {
        CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);
        return customerBO.saveCustomer(newCustomer);
    }
}
