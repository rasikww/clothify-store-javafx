package edu.icet.coursework.bo.product;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Product;
import javafx.collections.ObservableList;

public interface ProductBO extends SuperBO {
    boolean saveProduct(Product product);
    boolean deleteProductById(Integer id);
    Product getProduct(Integer productId);
    boolean updateProduct(Product product);
    ObservableList<Product> getAllProducts();
}
