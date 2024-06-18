package edu.icet.coursework.controller.orderDetail;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.orderDetail.OrderDetailBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.orderDetail.OrderDetailDAO;
import edu.icet.coursework.dto.OrderDetail;
import edu.icet.coursework.entity.OrderDetailEntity;
import edu.icet.coursework.util.BOType;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;

public class OrderDetailController {
    private static OrderDetailController instance;
    private OrderDetailController(){}
    public static OrderDetailController getInstance(){
        if (instance == null) {
            return instance = new OrderDetailController();
        }
        return instance;
    }
    //-------------------------------------------------

    OrderDetailBO orderDetailBO = BOFactory.getInstance().getBO(BOType.ORDER_DETAIL);

    public String generateNextOrderDetailId() {
        OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER_DETAIL);
        OrderDetailEntity lastOrderDetail = orderDetailDAO.getLast();
        if (lastOrderDetail == null) {
            return "1";
        }else return String.valueOf((lastOrderDetail.getOrderDetailId()+1));
    }

    public boolean addOrderDetail(OrderDetail newOrderDetail) {
        return orderDetailBO.saveOrderDetail(newOrderDetail);
    }

    public OrderDetail getOrderDetail(String orderDetailId) {
        return orderDetailBO.getOrderDetail(Integer.valueOf(orderDetailId));
    }

    public boolean removeOrderDetail(String orderDetailId) {
        return orderDetailBO.deleteOrderDetailById(Integer.valueOf(orderDetailId));
    }

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailBO.updateOrderDetail(orderDetail);
    }

    public ObservableList<OrderDetail> getAllOrderDetails() {
        return orderDetailBO.getAllOrderDetails();
    }
}
