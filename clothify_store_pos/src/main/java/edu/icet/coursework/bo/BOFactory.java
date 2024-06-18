package edu.icet.coursework.bo;

import edu.icet.coursework.bo.customer.impl.CustomerBOImpl;
import edu.icet.coursework.bo.order.impl.OrderBOImpl;
import edu.icet.coursework.bo.orderDetail.impl.OrderDetailBOImpl;
import edu.icet.coursework.bo.product.impl.ProductBOImpl;
import edu.icet.coursework.bo.supplier.impl.SupplierBOImpl;
import edu.icet.coursework.bo.user.impl.UserBOImpl;
import edu.icet.coursework.util.BOType;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory(){}
    public static BOFactory getInstance(){
        if (instance == null) {
            return instance = new BOFactory();
        }
        return instance;
    }
    //---------------------------------------
    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case CUSTOMER: return (T) new CustomerBOImpl();
            case ORDER: return (T) new OrderBOImpl();
            case ORDER_DETAIL: return (T) new OrderDetailBOImpl();
            case SUPPLIER: return (T) new SupplierBOImpl();
            case PRODUCT: return (T) new ProductBOImpl();
            case USER: return (T) new UserBOImpl();
            case REPORT:
                break;
        }
        return null;
    }
}
