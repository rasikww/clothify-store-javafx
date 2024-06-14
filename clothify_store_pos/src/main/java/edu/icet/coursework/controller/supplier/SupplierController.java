package edu.icet.coursework.controller.supplier;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.supplier.SupplierBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.supplier.SupplierDAO;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.entity.SupplierEntity;
import edu.icet.coursework.util.BOType;
import edu.icet.coursework.util.DAOType;

public class SupplierController {
    private static SupplierController instance;
    private SupplierController(){}
    public static SupplierController getInstance(){
        if (instance == null) {
            return instance = new SupplierController();
        }
        return instance;
    }
//--------------------------------------------------------
    SupplierBO supplierBO = BOFactory.getInstance().getBO(BOType.SUPPLIER);

    public String generateNextSupplierId() {
        SupplierDAO supplierDAO = DAOFactory.getInstance().getDAO(DAOType.SUPPLIER);
        SupplierEntity lastSupplier = supplierDAO.getLast();
        if (lastSupplier == null) {
            return "1";
        }else return String.valueOf((lastSupplier.getSupplierId()+1));
    }

    public boolean addSupplier(Supplier newSupplier) {
        return supplierBO.saveSupplier(newSupplier);
    }

    public Supplier getSupplier(String supplierId) {
        return supplierBO.getSupplier(Integer.valueOf(supplierId));
    }

    public boolean removeSupplier(String supplierId) {
        return supplierBO.deleteSupplierById(Integer.valueOf(supplierId));
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierBO.updateSupplier(supplier);
    }
}
