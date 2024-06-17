package edu.icet.coursework.dao.product.impl;

import edu.icet.coursework.dao.product.ProductDAO;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.entity.ProductEntity;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean save(ProductEntity entity) {
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
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            if (productEntity != null) {
                session.remove(productEntity);
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
    public ProductEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        ProductEntity productEntity = null;
        try {
            String sql = "SELECT * FROM product ORDER BY product_id DESC LIMIT 1";
            NativeQuery<ProductEntity> query = session.createNativeQuery(sql, ProductEntity.class);
            List<ProductEntity> results = query.list();

            if (!results.isEmpty()) {
                productEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return productEntity;
    }

    @Override
    public ProductEntity getById(Integer id) {
        ProductEntity productEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            productEntity = session.get(ProductEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return productEntity;
    }

    @Override
    public boolean update(ProductEntity newProductEntity) {
        boolean isUpdated = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            ProductEntity productEntity = session.get(ProductEntity.class, newProductEntity.getProductId());
            productEntity.setSupplierEntity(newProductEntity.getSupplierEntity());
            productEntity.setName(newProductEntity.getName());
            productEntity.setDescription(newProductEntity.getDescription());
            productEntity.setPrice(newProductEntity.getPrice());
            productEntity.setStockQuantity(newProductEntity.getStockQuantity());
            productEntity.setProductImageLink(newProductEntity.getProductImageLink());
            productEntity.setCategory(newProductEntity.getCategory());
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
    public ObservableList<Product> getAll() {
        ObservableList<Product> allProducts = FXCollections.observableArrayList();
        Session session = HibernateUtil.getInstance().getSession();
        try {
            String sql = "SELECT * FROM product WHERE deleted=0";
            NativeQuery<ProductEntity> query = session.createNativeQuery(sql, ProductEntity.class);
            List<ProductEntity> results = query.list();
            for (ProductEntity productEntity : results) {
                Product product = new ModelMapper().map(productEntity, Product.class);
                product.setSupplierId(productEntity.getSupplierEntity().getSupplierId());
                allProducts.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return allProducts;
    }
}
