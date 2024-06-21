package edu.icet.coursework.controller.receipt;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.receipt.ReceiptBO;
import edu.icet.coursework.dto.Receipt;
import edu.icet.coursework.util.BOType;

public class ReceiptController {
    private static ReceiptController instance;
    private ReceiptController(){}
    public static ReceiptController getInstance(){
        if (instance == null) {
            return instance = new ReceiptController();
        }
        return instance;
    }
    //--------------------------------------------

    ReceiptBO receiptBO = BOFactory.getInstance().getBO(BOType.RECEIPT);

//    public String generateNextReceiptId() {
//        ReceiptDAO receiptDAO = DAOFactory.getInstance().getDAO(DAOType.RECEIPT);
//        ReceiptEntity lastReceipt = receiptDAO.getLast();
//        if (lastReceipt == null) {
//            return "1";
//        }else return String.valueOf((lastReceipt.getReceiptId()+1));
//    }

    public boolean addReceipt(Receipt newReceipt) {
        return receiptBO.saveReceipt(newReceipt);
    }

    public Receipt getReceipt(String receiptId) {
        return receiptBO.getReceipt(Integer.valueOf(receiptId));
    }

    public boolean removeReceipt(String receiptId) {
        return receiptBO.deleteReceiptById(Integer.valueOf(receiptId));
    }

    public boolean updateReceipt(Receipt receipt) {
        return receiptBO.updateReceipt(receipt);
    }
}
