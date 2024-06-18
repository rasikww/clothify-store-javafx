package edu.icet.coursework.bo.order.impl;

import edu.icet.coursework.bo.order.OrderBO;
import edu.icet.coursework.controller.customer.CustomerController;
import edu.icet.coursework.controller.supplier.SupplierController;
import edu.icet.coursework.controller.user.UserController;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.order.OrderDAO;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.entity.*;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);
    @Override
    public boolean saveOrder(Order orderDTO) {
        List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();
        OrderEntity orderEntity = new ModelMapper().map(orderDTO, OrderEntity.class);
        orderEntity.setUserEntity(
                new ModelMapper().map(
                        UserController.getInstance().getUser(
                                String.valueOf(orderDTO.getUserId())), UserEntity.class));
        orderEntity.setCustomerEntity(
                new ModelMapper().map(
                        CustomerController.getInstance().getCustomer(
                                String.valueOf(orderDTO.getCustomerId())), CustomerEntity.class)
        );
        orderDTO.getOrderDetails().forEach(orderDetail -> {
            OrderDetailEntity orderDetailEntity = new ModelMapper().map(orderDetail, OrderDetailEntity.class);
            orderDetailEntity.setOrderEntity(orderEntity);
            ProductEntity productEntity = new ModelMapper().map(orderDetail.getProduct(), ProductEntity.class);
            SupplierEntity supplierEntity = new ModelMapper().map(
                    SupplierController.getInstance().getSupplier(
                            String.valueOf(orderDetail.getProduct().getSupplierId())),
                    SupplierEntity.class);
            productEntity.setSupplierEntity(supplierEntity);
            orderDetailEntityList.add(orderDetailEntity);
        });
        orderEntity.setOrderDetailEntities(orderDetailEntityList);
        return orderDAO.save(orderEntity);
    }

    @Override
    public boolean deleteOrderById(Integer orderId) {
        return orderDAO.deleteById(orderId);
    }

    @Override
    public Order getOrder(Integer orderId) {
        try {
            return new ModelMapper().map(orderDAO.getById(orderId), Order.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.update(new ModelMapper().map(order, OrderEntity.class));
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        return orderDAO.getAll();
    }
}
