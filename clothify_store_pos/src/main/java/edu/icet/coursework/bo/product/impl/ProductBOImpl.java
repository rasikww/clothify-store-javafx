package edu.icet.coursework.bo.product.impl;

import edu.icet.coursework.bo.product.ProductBO;
import edu.icet.coursework.controller.supplier.SupplierController;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.product.ProductDAO;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.entity.ProductEntity;
import edu.icet.coursework.entity.SupplierEntity;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = DAOFactory.getInstance().getDAO(DAOType.PRODUCT);
    @Override
    public boolean saveProduct(Product productDTO) {
        Supplier supplier = SupplierController.getInstance().getSupplier(
                String.valueOf(productDTO.getSupplierId()));
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        ProductEntity productEntity = new ModelMapper().map(productDTO, ProductEntity.class);
        productEntity.setSupplierEntity(supplierEntity);
        return productDAO.save(productEntity);
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
        Supplier supplier = SupplierController.getInstance().getSupplier(
                String.valueOf(productDTO.getSupplierId()));
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        ProductEntity productEntity = new ModelMapper().map(productDTO, ProductEntity.class);
        productEntity.setSupplierEntity(supplierEntity);
        return productDAO.update(productEntity);
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        return productDAO.getAll();
    }
}
