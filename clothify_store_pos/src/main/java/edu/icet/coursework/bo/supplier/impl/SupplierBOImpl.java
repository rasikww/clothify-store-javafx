package edu.icet.coursework.bo.supplier.impl;

import edu.icet.coursework.bo.supplier.SupplierBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.supplier.SupplierDAO;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = DAOFactory.getInstance().getDAO(DAOType.SUPPLIER);
    @Override
    public boolean saveSupplier(Supplier supplierDTO) {
        return supplierDAO.save(supplierDTO);
    }

    @Override
    public boolean deleteSupplierById(Integer supplierId) {
        return supplierDAO.deleteById(supplierId);
    }

    @Override
    public Supplier getSupplier(Integer supplierId) {
        try {
            return new ModelMapper().map(supplierDAO.getById(supplierId),Supplier.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return supplierDAO.update(supplier);
    }

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        return supplierDAO.getAll();
    }
}
