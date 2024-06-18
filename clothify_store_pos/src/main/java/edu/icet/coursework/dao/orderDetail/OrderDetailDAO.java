package edu.icet.coursework.dao.orderDetail;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.OrderDetail;
import edu.icet.coursework.entity.OrderDetailEntity;
import javafx.collections.ObservableList;

public interface OrderDetailDAO extends CrudDAO<OrderDetailEntity> {
    ObservableList<OrderDetail> getAll();
}
