package edu.icet.coursework.bo.order.impl;

import edu.icet.coursework.bo.order.OrderBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.order.OrderDAO;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.entity.OrderEntity;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);
    @Override
    public boolean saveOrder(Order orderDTO) {
        return orderDAO.save(orderDTO);
    }

    @Override
    public boolean deleteOrderById(Integer orderId) {
        return orderDAO.deleteById(orderId);
    }

    @Override
    public Order getOrder(Integer orderId) {
        try {
            OrderEntity orderEntity = orderDAO.getById(orderId);
            return new ModelMapper().map(orderEntity, Order.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.update(order);
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        return orderDAO.getAll();
    }
}
