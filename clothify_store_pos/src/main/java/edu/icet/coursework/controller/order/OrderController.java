package edu.icet.coursework.controller.order;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.order.OrderBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.order.OrderDAO;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.entity.OrderEntity;
import edu.icet.coursework.util.BOType;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;

public class OrderController {
    private static OrderController instance;
    private OrderController(){}
    public static OrderController getInstance(){
        if (instance == null) {
            return instance = new OrderController();
        }
        return instance;
    }
    //-------------------------------------------------

    OrderBO orderBO = BOFactory.getInstance().getBO(BOType.ORDER);

    public String generateNextOrderId() {
        OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);
        OrderEntity lastOrder = orderDAO.getLast();
        if (lastOrder == null) {
            return "1";
        }else return String.valueOf((lastOrder.getOrderId()+1));
    }

    public boolean addOrder(Order newOrder) {
        return orderBO.saveOrder(newOrder);
    }

    public Order getOrder(String orderId) {
        return orderBO.getOrder(Integer.valueOf(orderId));
    }

    public boolean removeOrder(String orderId) {
        return orderBO.deleteOrderById(Integer.valueOf(orderId));
    }

    public boolean updateOrder(Order order) {
        return orderBO.updateOrder(order);
    }

    public ObservableList<Order> getAllOrders() {
        return orderBO.getAllOrders();
    }
}
