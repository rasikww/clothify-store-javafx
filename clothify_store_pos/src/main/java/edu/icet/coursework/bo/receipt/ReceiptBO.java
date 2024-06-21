package edu.icet.coursework.bo.receipt;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Receipt;

public interface ReceiptBO extends SuperBO {
    boolean saveReceipt(Receipt receiptDTO);
    boolean deleteReceiptById(Integer id);
    Receipt getReceipt(Integer receiptId);
    boolean updateReceipt(Receipt receipt);
}
