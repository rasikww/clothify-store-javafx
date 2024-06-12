package edu.icet.coursework.bo;

import edu.icet.coursework.bo.customer.impl.CustomerBOImpl;
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
            case CUSTOMER:return (T) new CustomerBOImpl();
            case ORDER:
                break;
            case SUPPLIER:
                break;
            case PRODUCT:
                break;
            case REPORT:
                break;
        }
        return null;
    }
}
