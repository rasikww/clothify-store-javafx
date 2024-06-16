package edu.icet.coursework.controller.product;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.product.ProductBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.product.ProductDAO;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.entity.ProductEntity;
import edu.icet.coursework.util.BOType;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;

public class ProductController {
    private static ProductController instance;
    private ProductController(){}
    public static ProductController getInstance(){
        if (instance == null) return instance = new ProductController();
        return instance;
    }
//-------------------------------------------------------
    ProductBO productBO = BOFactory.getInstance().getBO(BOType.PRODUCT);
    
    public String generateNextProductId() {
        ProductDAO productDAO = DAOFactory.getInstance().getDAO(DAOType.PRODUCT);
        ProductEntity lastProduct = productDAO.getLast();
        if (lastProduct == null) {
            return "1";
        }else return String.valueOf((lastProduct.getProductId()+1));
    }

    public boolean addProduct(Product newProduct) {
        return productBO.saveProduct(newProduct);
    }

    public Product getProduct(String productId) {
        return productBO.getProduct(Integer.valueOf(productId));
    }

    public boolean removeProduct(String productId) {
        return productBO.deleteProductById(Integer.valueOf(productId));
    }

    public boolean updateProduct(Product product) {
        return productBO.updateProduct(product);
    }

    public ObservableList<Product> getAllProducts() {
        return productBO.getAllProducts();
    }
}
