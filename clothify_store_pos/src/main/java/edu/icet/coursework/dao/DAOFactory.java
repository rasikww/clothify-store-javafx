package edu.icet.coursework.dao;

import edu.icet.coursework.dao.customer.impl.CustomerDAOImpl;
import edu.icet.coursework.util.DAOType;

public class DAOFactory {
    private static DAOFactory instance;
    private DAOFactory(){}
    public static DAOFactory getInstance(){
        if (instance == null) {
            return instance = new DAOFactory();
        }
        return instance;
    }
    //--------------------------------
    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER: return (T) new CustomerDAOImpl();
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
