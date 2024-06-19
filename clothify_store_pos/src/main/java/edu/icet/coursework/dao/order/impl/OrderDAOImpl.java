package edu.icet.coursework.dao.order.impl;

import edu.icet.coursework.controller.product.ProductController;
import edu.icet.coursework.dao.order.OrderDAO;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.dto.OrderDetail;
import edu.icet.coursework.entity.*;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order order) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(order.getOrderId());
            orderEntity.setCustomerEntity(session.get(CustomerEntity.class,order.getCustomerId()));
            orderEntity.setOrderDateTime(order.getOrderDateTime());
            orderEntity.setTotalCost(order.getTotalCost());
            orderEntity.setUserEntity(session.get(UserEntity.class,order.getUserId()));

            List<OrderDetailEntity> orderDetailEntities = order.getOrderDetails().stream()
                    .map(orderDetail -> {
                        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                        orderDetailEntity.setQuantity(orderDetail.getQuantity());
                        orderDetailEntity.setTotalPrice(orderDetail.getTotalPrice());

                        ProductEntity productEntity =
                                session.get(
                                        ProductEntity.class,
                                        orderDetail.getProduct().getProductId()
                                );
                        orderDetailEntity.setProductEntity(productEntity);
                        orderDetailEntity.setOrderEntity(orderEntity);
                        return orderDetailEntity;
                    })
                    .collect(Collectors.toList());
            orderEntity.setOrderDetailEntities(orderDetailEntities);

            for (OrderDetailEntity orderDetailEntity : orderEntity.getOrderDetailEntities()) {
                Integer requiredQty = orderDetailEntity.getQuantity();
                ProductEntity productEntity = orderDetailEntity.getProductEntity();
                Integer stockQty = productEntity.getStockQuantity();
                productEntity.setStockQuantity(stockQty-requiredQty);
            }

            session.persist(orderEntity);

            session.getTransaction().commit();
            isSaved = true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }

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
            OrderEntity orderEntity = session.get(OrderEntity.class, id);
            if (orderEntity != null) {
                session.remove(orderEntity);
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
    public OrderEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        OrderEntity orderEntity = null;
        try {
            String sql = "SELECT * FROM _order ORDER BY order_id DESC LIMIT 1";
            NativeQuery<OrderEntity> query = session.createNativeQuery(sql, OrderEntity.class);
            List<OrderEntity> results = query.list();

            if (!results.isEmpty()) {
                orderEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return orderEntity;
    }


    @Override
    public OrderEntity getById(Integer id) {
        OrderEntity orderEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            orderEntity = session.get(OrderEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return orderEntity;
    }


    @Override
    public boolean update(Order newOrder) {
        boolean isUpdated = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();

            OrderEntity orderEntity = session.get(OrderEntity.class, newOrder.getOrderId());
            orderEntity.setCustomerEntity(session.get(CustomerEntity.class,newOrder.getCustomerId()));
            session.flush();
            session.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
       return isUpdated;
    }

    @Override
    public ObservableList<Order> getAll() {
        ObservableList<Order> allOrders = FXCollections.observableArrayList();
        Session session = HibernateUtil.getInstance().getSession();
        try {
            String sql = "SELECT * FROM order WHERE deleted=0";
            NativeQuery<OrderEntity> query = session.createNativeQuery(sql, OrderEntity.class);
            List<OrderEntity> results = query.list();
            List<OrderDetail> orderDetails = null;
            for (OrderEntity orderEntity : results) {
                Order order = new ModelMapper().map(orderEntity, Order.class);
                order.setCustomerId(orderEntity.getCustomerEntity().getCustomerId());
                order.setUserId(orderEntity.getUserEntity().getUserId());
                orderEntity.getOrderDetailEntities().forEach(orderDetailEntity -> {
                    OrderDetail orderDetail = new OrderDetail(
                            orderDetailEntity.getQuantity(),
                            orderDetailEntity.getOrderEntity().getOrderId(),
                            ProductController.getInstance().getProduct(
                                    String.valueOf(orderDetailEntity.getProductEntity().getProductId())),
                            orderDetailEntity.getTotalPrice()
                    );
                    orderDetails.add(orderDetail);
                });

                order.setOrderDetails(orderDetails);
                allOrders.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return allOrders;
    }
}
