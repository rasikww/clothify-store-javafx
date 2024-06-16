package edu.icet.coursework.dao.product;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.entity.ProductEntity;
import javafx.collections.ObservableList;

public interface ProductDAO extends CrudDAO<ProductEntity> {
    ObservableList<Product> getAll();
}
