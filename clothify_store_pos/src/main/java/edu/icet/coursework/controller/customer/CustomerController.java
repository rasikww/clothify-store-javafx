package edu.icet.coursework.controller.customer;

public class CustomerController {
    private static CustomerController instance;
    private CustomerController(){}
    public static CustomerController getInstance(){
        if (instance == null) {
            return instance = new CustomerController();
        }
        return instance;
    }
    //--------------------------------------
    public String generateNextCustomerId() {

    }

}
