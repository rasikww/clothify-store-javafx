package edu.icet.coursework.dao.supplier;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.entity.SupplierEntity;
import javafx.collections.ObservableList;

public interface SupplierDAO extends CrudDAO<SupplierEntity> {
    ObservableList<Supplier> getAll();
}
