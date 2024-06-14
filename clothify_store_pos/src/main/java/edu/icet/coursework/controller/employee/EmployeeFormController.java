package edu.icet.coursework.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.controller.customer.CustomerController;
import edu.icet.coursework.controller.supplier.SupplierController;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.dto.Supplier;
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
    public JFXButton btnAddSupplier;
    public Label lblSupplierIdAdd;
    public JFXTextField txtSupplierNameAdd;
    public JFXTextField txtSupplierEmailAdd;
    public JFXTextField txtSupplierPhoneNoAdd;
    public JFXTextField txtSupplierCompanyAdd;
    public JFXButton btnRefreshSupplierAdd;
    public JFXButton btnRemoveSupplier;
    public Label lblSupplierIdRemove;
    public Label lblSupplierNameRemove;
    public Label lblSupplierCompanyRemove;
    public Label lblSupplierEmailRemove;
    public Label lblSupplierPhoneNoRemove;
    public JFXButton btnRefreshSupplierRemove;
    public JFXTextField txtSupplierIdRemove;
    public JFXButton btnSearchSupplierRemove;
    private User loggedInUser;
    private String nextCustomerId;
    private String nextSupplierId;
    private Customer searchedCustomer;
    private Supplier searchedSupplier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            lblEmployeeId.setText(String.valueOf(loggedInUser.getUserId()));
            lblEmployeeName.setText(loggedInUser.getName());
        });
        displayNextCustomerId();
        displayNextSupplierId();
    }

    private void displayNextSupplierId() {
        nextSupplierId = getNextSupplierId();
        lblSupplierIdAdd.setText(nextSupplierId);
    }

    private String getNextSupplierId() {
        return SupplierController.getInstance().generateNextSupplierId();
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
            refreshProcessCustomerAdd();
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can't add Customer").show();
        }
    }
    private void addSupplierProcess() {
        String supplierName = txtSupplierNameAdd.getText();
        String supplierCompany = txtSupplierCompanyAdd.getText();
        String supplierEmail = txtSupplierEmailAdd.getText();
        String supplierPhoneNo = txtSupplierPhoneNoAdd.getText();

        Supplier supplier = new Supplier(
                Integer.parseInt(nextSupplierId) ,
                supplierName,
                supplierCompany,
                supplierEmail,
                supplierPhoneNo
        );

        boolean isAdded = SupplierController.getInstance().addSupplier(supplier);
        if(isAdded){
            refreshProcessSupplierAdd();
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can't add Supplier").show();
        }
    }

    private void refreshProcessSupplierAdd() {
        displayNextSupplierId();
        txtSupplierNameAdd.clear();
        txtSupplierCompanyAdd.clear();
        txtSupplierEmailAdd.clear();
        txtSupplierPhoneNoAdd.clear();
    }

    public void txtCustomerPhoneNoAddOnAction(ActionEvent actionEvent) {
        addCustomerProcess();
    }

    public void btnRefreshCustomerAddOnAction(ActionEvent actionEvent) {
        refreshProcessCustomerAdd();
    }
    private void displayNextCustomerId(){
        nextCustomerId = getNextCustomerId();
        lblCustomerId.setText(nextCustomerId);
    }
    private void refreshProcessCustomerAdd(){
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
    private void refreshProcessCustomerRemove(){
        txtCustomerIdRemove.clear();
        txtCustomerNameRemove.clear();
        txtCustomerEmailRemove.clear();
        txtCustomerPhoneNoRemove.clear();
    }
    private void refreshProcessCustomerUpdate(){
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
        refreshProcessCustomerRemove();
    }

    public void btnRefreshCustomerRemoveOnAction(ActionEvent actionEvent) {
        refreshProcessCustomerRemove();
    }

    public void txtCustomerPhoneNoUpdateOnAction(ActionEvent actionEvent) {
        addSupplierProcess();
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
        refreshProcessCustomerUpdate();
    }

    public void btnRefreshCustomerUpdate(ActionEvent actionEvent) {
        refreshProcessCustomerUpdate();
    }
    public void txtCustomerIdUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateCustomerData();
    }
    public void btnSearchCustomerUpdateOnAction(ActionEvent actionEvent){
        displayUpdateCustomerData();
    }
//----------------------------------------
    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        addSupplierProcess();
    }
    public void txtSupplierPhoneNoAddOnAction(ActionEvent actionEvent) {
        addSupplierProcess();
    }
    public void btnRefreshSupplierAddOnAction(ActionEvent actionEvent) {
        refreshProcessSupplierAdd();
    }
//--------------------------------------------
    public void btnRemoveSupplierOnAction(ActionEvent actionEvent) {
        boolean isRemoved = SupplierController.getInstance().removeSupplier(txtSupplierIdRemove.getText());
        if (isRemoved){
            new Alert(Alert.AlertType.CONFIRMATION,"Removed the Supplier").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while removing the Supplier").show();
        }
        refreshProcessSupplierRemove();
    }

    public void btnRefreshSupplierRemoveOnAction(ActionEvent actionEvent) {
        refreshProcessSupplierRemove();
    }

    private void refreshProcessSupplierRemove() {
        txtSupplierIdRemove.clear();
        lblSupplierIdRemove.setText(null);
        lblSupplierNameRemove.setText(null);
        lblSupplierCompanyRemove.setText(null);
        lblSupplierEmailRemove.setText(null);
        lblSupplierPhoneNoRemove.setText(null);
    }

    public void txtSupplierIdRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveSupplierData();
    }

    public void btnSearchSupplierRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveSupplierData();
    }

    private void displayRemoveSupplierData() {
        Supplier supplier = searchSupplier(txtSupplierIdRemove.getText());
        if (supplier == null) {
            new Alert(Alert.AlertType.ERROR,"No such Supplier").show();
        }else{
            lblSupplierIdRemove.setText(String.valueOf(supplier.getSupplierId()));
            lblSupplierNameRemove.setText(supplier.getName());
            lblSupplierCompanyRemove.setText(supplier.getCompany());
            lblSupplierEmailRemove.setText(supplier.getEmail());
            lblSupplierPhoneNoRemove.setText(supplier.getPhoneNumber());
        }
    }

    private Supplier searchSupplier(String supplierId) {
        return searchedSupplier = SupplierController.getInstance().getSupplier(supplierId);
    }
}
