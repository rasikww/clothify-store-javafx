package edu.icet.coursework.bo.order;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Order;
import javafx.collections.ObservableList;

public interface OrderBO extends SuperBO {
    boolean saveOrder(Order order);
    boolean deleteOrderById(Integer id);
    Order getOrder(Integer orderId);
    boolean updateOrder(Order order);
    ObservableList<Order> getAllOrders();
}
