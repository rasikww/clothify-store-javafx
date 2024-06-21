package edu.icet.coursework.bo.receipt.impl;

import edu.icet.coursework.bo.receipt.ReceiptBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.receipt.ReceiptDAO;
import edu.icet.coursework.dto.Receipt;
import edu.icet.coursework.entity.ReceiptEntity;
import edu.icet.coursework.util.DAOType;
import org.modelmapper.ModelMapper;

public class ReceiptBOImpl implements ReceiptBO {
    ReceiptDAO receiptDAO = DAOFactory.getInstance().getDAO(DAOType.RECEIPT);
    @Override
    public boolean saveReceipt(Receipt receiptDTO) {
        return receiptDAO.save(receiptDTO);
    }

    @Override
    public boolean deleteReceiptById(Integer receiptId) {
        return receiptDAO.deleteById(receiptId);
    }

    @Override
    public Receipt getReceipt(Integer receiptId) {
        try {
            ReceiptEntity receiptEntity = receiptDAO.getById(receiptId);
            return new ModelMapper().map(receiptEntity, Receipt.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateReceipt(Receipt receiptDTO) {
        return receiptDAO.update(receiptDTO);
    }

    @Override
    public boolean viewLastReceipt() {
        return receiptDAO.viewLastReceipt();
    }
}
