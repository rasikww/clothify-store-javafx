package edu.icet.coursework.bo.orderDetail.impl;

import edu.icet.coursework.bo.orderDetail.OrderDetailBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.orderDetail.OrderDetailDAO;
import edu.icet.coursework.dto.OrderDetail;
import edu.icet.coursework.entity.OrderDetailEntity;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER_DETAIL);
    @Override
    public boolean saveOrderDetail(OrderDetail orderDetailDTO) {
        OrderDetailEntity orderDetailEntity = new ModelMapper().map(orderDetailDTO, OrderDetailEntity.class);
//        orderDetailEntity.
        return orderDetailDAO.save(orderDetailEntity);
    }

    @Override
    public boolean deleteOrderDetailById(Integer orderDetailId) {
        return orderDetailDAO.deleteById(orderDetailId);
    }

    @Override
    public OrderDetail getOrderDetail(Integer orderDetailId) {
        try {
            return new ModelMapper().map(orderDetailDAO.getById(orderDetailId), OrderDetail.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailDAO.update(new ModelMapper().map(orderDetail, OrderDetailEntity.class));
    }

    @Override
    public ObservableList<OrderDetail> getAllOrderDetails() {
        return orderDetailDAO.getAll();
    }
}
