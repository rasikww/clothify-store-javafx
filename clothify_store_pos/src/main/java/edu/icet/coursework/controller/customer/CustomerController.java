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
    CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public String generateNextCustomerId() {
        CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
        CustomerEntity lastCustomer = customerDAO.getLast();
        if (lastCustomer == null) {
            return "1";
        }else return String.valueOf((lastCustomer.getCustomerId()+1));
    }

    public boolean addCustomer(Customer newCustomer) {
        return customerBO.saveCustomer(newCustomer);
    }

    public Customer getCustomer(String customerId) {
        return customerBO.getCustomer(Integer.valueOf(customerId));
    }

    public boolean removeCustomer(String customerId) {
        return customerBO.deleteCustomerById(Integer.valueOf(customerId));
    }

    public boolean updateCustomer(Customer customer) {
        return customerBO.updateCustomer(customer);
    }
}
