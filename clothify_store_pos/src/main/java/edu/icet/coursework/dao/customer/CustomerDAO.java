package edu.icet.coursework.dao.customer;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.entity.CustomerEntity;
import javafx.collections.ObservableList;

public interface CustomerDAO extends CrudDAO<CustomerEntity,Customer> {
    ObservableList<Customer> getAll();
}
