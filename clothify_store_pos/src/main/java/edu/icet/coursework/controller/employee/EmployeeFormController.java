package edu.icet.coursework.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.controller.customer.CustomerController;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.dto.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    public Label lblEmployeeName;
    public Label lblEmployeeId;
    public Label lblLogout;
    public Label lblCustomerId;
    public JFXTextField txtCustomerNameAdd;
    public JFXTextField txtCustomerEmailAdd;
    public JFXTextField txtCustomerPhoneNoAdd;
    public JFXButton btnAddCustomer;
    public JFXButton btnRefreshCustomerAdd;
    public JFXTextField txtCustomerIdRemove;
    public JFXButton btnSearchCustomerRemove;
    public JFXTextField txtCustomerNameRemove;
    public JFXTextField txtCustomerEmailRemove;
    public JFXTextField txtCustomerPhoneNoRemove;
    public JFXButton btnRemoveCustomer;
    public JFXButton btnRefreshCustomerRemove;
    public JFXTextField txtCustomerNameUpdate;
    public JFXTextField txtCustomerEmailUpdate;
    public JFXTextField txtCustomerPhoneNoUpdate;
    public JFXButton btnUpdateCustomer;
    public JFXButton btnRefreshCustomerUpdate;
    public JFXTextField txtCustomerIdUpdate;
    public JFXButton btnSearchCustomerUpdate;
    private User loggedInUser;
    private String nextCustomerId;
    private Customer searchedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            lblEmployeeId.setText(String.valueOf(loggedInUser.getUserId()));
            lblEmployeeName.setText(loggedInUser.getName());
        });
        displayNextCustomerId();
    }

    private String getNextCustomerId() {
        return CustomerController.getInstance().generateNextCustomerId();
    }


    public void initUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        addCustomerProcess();
    }

    private void addCustomerProcess() {
        String customerName = txtCustomerNameAdd.getText();
        String customerEmail = txtCustomerEmailAdd.getText();
        String customerPhoneNo = txtCustomerPhoneNoAdd.getText();

        Customer customer = new Customer(
                Integer.parseInt(nextCustomerId) ,
                customerName,
                customerEmail,
                customerPhoneNo
        );

        boolean isAdded = CustomerController.getInstance().addCustomer(customer);
        if(isAdded){
            refreshProcessAdd();
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can't add Customer").show();
        }
    }

    public void txtCustomerPhoneNoAddOnAction(ActionEvent actionEvent) {
        addCustomerProcess();
    }

    public void btnRefreshCustomerAddOnAction(ActionEvent actionEvent) {
        refreshProcessAdd();
    }
    private void displayNextCustomerId(){
        nextCustomerId = getNextCustomerId();
        lblCustomerId.setText(nextCustomerId);
    }
    private void refreshProcessAdd(){
        txtCustomerNameAdd.clear();
        txtCustomerEmailAdd.clear();
        txtCustomerPhoneNoAdd.clear();
        displayNextCustomerId();
    }

    public void txtCustomerIdRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveCustomerData();
    }

    public void btnSearchCustomerRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveCustomerData();
    }
    private Customer searchCustomer(String customerId){
        return searchedCustomer = CustomerController.getInstance().getCustomer(customerId);
    }
    private void displayRemoveCustomerData(){
        Customer customer = searchCustomer(txtCustomerIdRemove.getText());
        if (customer == null) {
            new Alert(Alert.AlertType.ERROR,"No such Customer").show();
        }else{
            txtCustomerNameRemove.setText(customer.getName());
            txtCustomerEmailRemove.setText(customer.getEmail());
            txtCustomerPhoneNoRemove.setText(customer.getPhoneNumber());
        }
    }
    private void refreshProcessRemove(){
        txtCustomerIdRemove.clear();
        txtCustomerNameRemove.clear();
        txtCustomerEmailRemove.clear();
        txtCustomerPhoneNoRemove.clear();
    }
    private void refreshProcessUpdate(){
        txtCustomerIdUpdate.clear();
        txtCustomerNameUpdate.clear();
        txtCustomerEmailUpdate.clear();
        txtCustomerPhoneNoUpdate.clear();
    }

    public void btnRemoveCustomerOnAction(ActionEvent actionEvent) {
        boolean isRemoved = CustomerController.getInstance().removeCustomer(txtCustomerIdRemove.getText());
        if (isRemoved){
            new Alert(Alert.AlertType.CONFIRMATION,"Removed the customer").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while removing the customer").show();
        }
        refreshProcessRemove();
    }

    public void btnRefreshCustomerRemoveOnAction(ActionEvent actionEvent) {
        refreshProcessRemove();
    }

    public void txtCustomerPhoneNoUpdateOnAction(ActionEvent actionEvent) {

    }
    private void displayUpdateCustomerData(){
        Customer customer = searchCustomer(txtCustomerIdUpdate.getText());
        if (customer == null) {
            new Alert(Alert.AlertType.ERROR,"No such Customer").show();
        }else{
            txtCustomerNameUpdate.setText(customer.getName());
            txtCustomerEmailUpdate.setText(customer.getEmail());
            txtCustomerPhoneNoUpdate.setText(customer.getPhoneNumber());
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        String customerName = txtCustomerNameUpdate.getText();
        String customerEmail = txtCustomerEmailUpdate.getText();
        String customerPhoneNo = txtCustomerPhoneNoUpdate.getText();

        Customer customer = new Customer(
                searchedCustomer.getCustomerId() ,
                customerName,
                customerEmail,
                customerPhoneNo
        );

        boolean isUpdated = CustomerController.getInstance().updateCustomer(customer);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated the customer").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while updating the customer").show();
        }
        refreshProcessUpdate();
    }

    public void btnRefreshCustomerUpdate(ActionEvent actionEvent) {
        refreshProcessUpdate();
    }

    public void txtCustomerIdUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateCustomerData();
    }
    public void btnSearchCustomerUpdateOnAction(ActionEvent actionEvent){
        displayUpdateCustomerData();
    }
}
