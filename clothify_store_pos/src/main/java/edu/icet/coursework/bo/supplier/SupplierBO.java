package edu.icet.coursework.bo.supplier;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Supplier;

public interface SupplierBO extends SuperBO {
    boolean saveSupplier(Supplier supplier);
    boolean deleteSupplierById(Integer id);
    Supplier getSupplier(Integer supplierId);
    boolean updateSupplier(Supplier supplier);
}
