package edu.icet.coursework.dao.orderDetail.impl;

import edu.icet.coursework.dao.orderDetail.OrderDetailDAO;
import edu.icet.coursework.dto.OrderDetail;
import edu.icet.coursework.entity.OrderDetailEntity;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetailEntity entity) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            isSaved = true;
            System.out.println("in try: "+ entity);
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println("in catch");

        }finally {
            session.close();
        }
        return isSaved;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean isDeleted = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            OrderDetailEntity orderDetailEntity = session.get(OrderDetailEntity.class, id);
            if (orderDetailEntity != null) {
                session.remove(orderDetailEntity);
                session.getTransaction().commit();
                isDeleted = true;
            }
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return isDeleted;
    }

    @Override
    public OrderDetailEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        OrderDetailEntity orderDetailEntity = null;
        try {
            String sql = "SELECT * FROM order_detail ORDER BY order_detail_id DESC LIMIT 1";
            NativeQuery<OrderDetailEntity> query = session.createNativeQuery(sql, OrderDetailEntity.class);
            List<OrderDetailEntity> results = query.list();

            if (!results.isEmpty()) {
                orderDetailEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return orderDetailEntity;
    }

    @Override
    public OrderDetailEntity getById(Integer id) {
        OrderDetailEntity orderDetailEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            orderDetailEntity = session.get(OrderDetailEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return orderDetailEntity;
    }

    @Override
    public boolean update(OrderDetailEntity newOrderDetailEntity) {
        boolean isUpdated = false;
//        Session session = HibernateUtil.getInstance().getSession();
//        try {
//            session.getTransaction().begin();
//            OrderDetailEntity orderDetailEntity = session.get(OrderDetailEntity.class, newOrderDetailEntity.getOrderDetailId());
//            orderDetailEntity.setCustomerEntity(newOrderDetailEntity.getCustomerEntity());
//            session.flush();
//            session.getTransaction().commit();
//            isUpdated = true;
//        } catch (Exception e) {
//            if (session.getTransaction().isActive()){
//                session.getTransaction().rollback();
//            }
//        } finally {
//            session.close();
//        }
        return isUpdated;
    }

    @Override
    public ObservableList<OrderDetail> getAll() {
        ObservableList<OrderDetail> allOrderDetails = FXCollections.observableArrayList();
//        Session session = HibernateUtil.getInstance().getSession();
//        try {
//            String sql = "SELECT * FROM orderDetail WHERE deleted=0";
//            NativeQuery<OrderDetailEntity> query = session.createNativeQuery(sql, OrderDetailEntity.class);
//            List<OrderDetailEntity> results = query.list();
//            List<OrderDetailDetail> orderDetailDetails = null;
//            for (OrderDetailEntity orderDetailEntity : results) {
//                OrderDetail orderDetail = new ModelMapper().map(orderDetailEntity, OrderDetail.class);
//                orderDetail.setCustomerId(orderDetailEntity.getCustomerEntity().getCustomerId());
//                orderDetail.setUserId(orderDetailEntity.getUserEntity().getUserId());
//                orderDetailEntity.getOrderDetailDetailEntities().forEach(orderDetailDetailEntity -> {
//                    OrderDetailDetail orderDetailDetail = new OrderDetailDetail(
//                            orderDetailDetailEntity.getQuantity(),
//                            orderDetailDetailEntity.getOrderDetailEntity().getOrderDetailId(),
//                            ProductController.getInstance().getProduct(
//                                    String.valueOf(orderDetailDetailEntity.getProductEntity().getProductId())),
//                            orderDetailDetailEntity.getTotalPrice()
//                    );
//                    orderDetailDetails.add(orderDetailDetail);
//                });
//
//                orderDetail.setOrderDetailDetails(orderDetailDetails);
//                allOrderDetails.add(orderDetail);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            session.close();
//        }
        return allOrderDetails;
    }
}
