package edu.icet.coursework.bo.product.impl;

import edu.icet.coursework.bo.product.ProductBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.product.ProductDAO;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.entity.ProductEntity;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = DAOFactory.getInstance().getDAO(DAOType.PRODUCT);
    @Override
    public boolean saveProduct(Product productDTO) {
        return productDAO.save(productDTO);
    }

    @Override
    public boolean deleteProductById(Integer productId) {
        return productDAO.deleteById(productId);
    }

    @Override
    public Product getProduct(Integer productId) {
        try {
            ProductEntity productEntity = productDAO.getById(productId);
            Product product = new ModelMapper().map(productEntity, Product.class);
            product.setSupplierId(productEntity.getSupplierEntity().getSupplierId());
            return product;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateProduct(Product productDTO) {
        return productDAO.update(productDTO);
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        return productDAO.getAll();
    }
}
