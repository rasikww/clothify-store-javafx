package edu.icet.coursework.bo.orderDetail;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.OrderDetail;
import javafx.collections.ObservableList;

public interface OrderDetailBO extends SuperBO {
    boolean saveOrderDetail(OrderDetail orderDetail);
    boolean deleteOrderDetailById(Integer id);
    OrderDetail getOrderDetail(Integer orderDetailId);
    boolean updateOrderDetail(OrderDetail orderDetail);
    ObservableList<OrderDetail> getAllOrderDetails();
}
