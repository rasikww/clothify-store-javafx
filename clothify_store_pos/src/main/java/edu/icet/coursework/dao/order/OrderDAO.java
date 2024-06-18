package edu.icet.coursework.dao.order;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.entity.OrderEntity;
import javafx.collections.ObservableList;

public interface OrderDAO extends CrudDAO<OrderEntity> {
    ObservableList<Order> getAll();
}
