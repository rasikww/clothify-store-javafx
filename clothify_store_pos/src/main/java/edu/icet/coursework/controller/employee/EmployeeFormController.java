package edu.icet.coursework.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.controller.customer.CustomerController;
import edu.icet.coursework.controller.product.ProductController;
import edu.icet.coursework.controller.supplier.SupplierController;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.dto.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public JFXButton btnUpdateSupplier;
    public JFXTextField txtSupplierIdUpdate;
    public Label lblSupplierIdUpdate;
    public JFXTextField txtSupplierNameUpdate;
    public JFXTextField txtSupplierEmailUpdate;
    public JFXTextField txtSupplierPhoneNoUpdate;
    public JFXTextField txtSupplierCompanyUpdate;
    public JFXButton btnSearchSupplierUpdate;
    public ComboBox<String> cmbSupplierAddProduct;
    public Label lblSupplierNameAddProduct;
    public Label lblSupplierCompanyAddProduct;
    public Label lblSupplierEmailAddProduct;
    public Label lblSupplierPhoneNoAddProduct;
    public JFXTextField txtProductNameAddProduct;
    public JFXTextArea txtProductDescAddProduct;
    public JFXTextField txtProductUnitPriceAddProduct;
    public JFXTextField txtStockQtyAddProduct;
    public JFXTextField txtImageLinkAddProduct;
    public Label lblProductIdAddProduct;
    public JFXButton btnAddProduct;
    public JFXButton btnRefreshAddProduct;
    public JFXButton btnRefreshUpdateSupplier;
    public Tab tabSuppliers;
    public TableColumn<Supplier,Integer> colSupplierId;
    public TableColumn<Supplier,String> colSupplierName;
    public TableColumn<Supplier,String> colSupplierCompany;
    public TableColumn<Supplier,String> colSupplierEmail;
    public TableColumn<Supplier,String> colSupplierPhoneNo;
    public TableView<Supplier> tblSuppliers;
    public Tab tabProducts;
    public ComboBox<String> cmbCategoryAddProduct;
    public JFXButton btnSearchProductRemove;
    public Label lblProductIdRemove;
    public Label lblProductName;
    public Label lblProductDesc;
    public Label lblProductCategory;
    public Label lblUnitPrice;
    public Label lblStockQuantity;
    public Label lblProductImageLink;
    public JFXButton btnRefreshRemoveProduct;
    public JFXTextField txtProductIdRemove;
    public JFXButton btnRemoveProduct;
    private User loggedInUser;
    private String nextCustomerId;
    private String nextSupplierId;
    private String nextProductId;
    private Customer searchedCustomer;
    private Supplier searchedSupplier;
    private Product searchedProduct;

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

    public void btnSearchSupplierUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateSupplierData();
    }
    public void txtSupplierIdUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateSupplierData();
    }
    private void displayUpdateSupplierData(){
        var supplier = searchSupplier(txtSupplierIdUpdate.getText());
        if (supplier == null) {
            new Alert(Alert.AlertType.ERROR,"No such Supplier").show();
        }else{
            lblSupplierIdUpdate.setText(String.valueOf(supplier.getSupplierId()));
            txtSupplierNameUpdate.setText(supplier.getName());
            txtSupplierCompanyUpdate.setText(supplier.getCompany());
            txtSupplierEmailUpdate.setText(supplier.getEmail());
            txtSupplierPhoneNoUpdate.setText(supplier.getPhoneNumber());
        }
    }
    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        String supplierName = txtSupplierNameUpdate.getText();
        String supplierCompany = txtSupplierCompanyUpdate.getText();
        String supplierEmail = txtSupplierEmailUpdate.getText();
        String supplierPhoneNo = txtSupplierPhoneNoUpdate.getText();

        Supplier supplier = new Supplier(
                searchedSupplier.getSupplierId() ,
                supplierName,
                supplierCompany,
                supplierEmail,
                supplierPhoneNo
        );

        boolean isUpdated = SupplierController.getInstance().updateSupplier(supplier);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated the supplier").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while updating the supplier").show();
        }
        refreshProcessSupplierUpdate();
    }

    private void refreshProcessSupplierUpdate() {
        txtSupplierIdUpdate.clear();
        lblSupplierIdUpdate.setText(null);
        txtSupplierNameUpdate.clear();
        txtSupplierCompanyUpdate.clear();
        txtSupplierEmailUpdate.clear();
        txtSupplierPhoneNoUpdate.clear();
        loadSuppliersTable();
    }

    public void txtSupplierPhoneNoUpdateOnAction(ActionEvent actionEvent) {
        btnUpdateSupplierOnAction(actionEvent);
    }
    public void btnRefreshUpdateSupplierOnAction(ActionEvent actionEvent) {
        refreshProcessSupplierUpdate();
    }
    public void tabSuppliersOnChanged(Event event) {
        loadSuppliersTable();
    }

    private void loadSuppliersTable() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        ObservableList<Supplier> allSuppliers = SupplierController.getInstance().getAllSuppliers();
        tblSuppliers.setItems(allSuppliers);
    }

    //-------------------------------------------------------------------------
    public void txtImageLinkAddProductOnAction(ActionEvent actionEvent) {
        addProductProcess();
    }

    public void btnAddProductOnAction(ActionEvent actionEvent) {
        addProductProcess();
    }

    private void addProductProcess() {
        var supplierId = SupplierController.getInstance().getAllSuppliers().get(
                cmbSupplierAddProduct.getSelectionModel().getSelectedIndex()).getSupplierId();
        String productName = txtProductNameAddProduct.getText();
        String productDescription = txtProductDescAddProduct.getText();
        Double unitPrice = Double.valueOf(txtProductUnitPriceAddProduct.getText());
        Integer stockQuantity = Integer.valueOf(txtStockQtyAddProduct.getText());
        String link = txtImageLinkAddProduct.getText();
        String category = cmbCategoryAddProduct.getSelectionModel().getSelectedItem();

        Product product = new Product(
                Integer.parseInt(nextProductId),
                supplierId,
                productName,
                productDescription,
                unitPrice,
                stockQuantity,
                link,
                category
        );

        boolean isAdded = ProductController.getInstance().addProduct(product);
        if(isAdded){
            refreshProcessAddProduct();
            new Alert(Alert.AlertType.CONFIRMATION,"Product Added").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can't add Product").show();
        }
    }

    public void btnRefreshAddProductOnAction(ActionEvent actionEvent) {
        refreshProcessAddProduct();
    }
    private void refreshProcessAddProduct(){
        displayNextProductId();
        cmbSupplierAddProduct.getSelectionModel().select(0);
        txtProductNameAddProduct.clear();
        txtProductDescAddProduct.clear();
        cmbCategoryAddProduct.getSelectionModel().select(0);
        txtProductUnitPriceAddProduct.clear();
        txtStockQtyAddProduct.clear();
        txtImageLinkAddProduct.clear();
    }
    public void tabProductsOnChanged(Event event) {
        displayNextProductId();
        loadComboBoxSupplier();
        loadComboBoxProductCategory();
    }

    private void displayNextProductId() {
        nextProductId = getNextProductId();
        lblProductIdAddProduct.setText(nextProductId);
    }

    private String getNextProductId() {
        return ProductController.getInstance().generateNextProductId();
    }

    private void loadComboBoxProductCategory() {
        ObservableList<String> categories = FXCollections.observableArrayList("Ladies", "Gents", "Kids");
        cmbCategoryAddProduct.setItems(categories);
    }

    private void loadComboBoxSupplier() {
        ObservableList<Supplier> allSuppliers = SupplierController.getInstance().getAllSuppliers();
        ObservableList<String> dropDownItems = FXCollections.observableArrayList();
        allSuppliers.forEach(supplier -> {
            dropDownItems.add(String.format("%d - %s",supplier.getSupplierId(),supplier.getName()));
        });
        cmbSupplierAddProduct.setItems(dropDownItems);
    }

    public void cmbSupplierAddProductOnAction(ActionEvent actionEvent) {
        displaySelectedSupplierAddProduct();
    }

    private void displaySelectedSupplierAddProduct() {
        int selectedIndex = cmbSupplierAddProduct.getSelectionModel().getSelectedIndex();
        Supplier supplier = SupplierController.getInstance().getAllSuppliers().get(selectedIndex);
        lblSupplierNameAddProduct.setText(supplier.getName());
        lblSupplierCompanyAddProduct.setText(supplier.getCompany());
        lblSupplierEmailAddProduct.setText(supplier.getEmail());
        lblSupplierPhoneNoAddProduct.setText(supplier.getPhoneNumber());
    }

    public void cmbCategoryAddProductOnAction(ActionEvent actionEvent) {
    }
//-----------------------------------------------------------Product Remove section starts here-----------
    public void btnSearchProductRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveProductData();
    }

    private void displayRemoveProductData() {
        Product product = searchProduct(txtProductIdRemove.getText());
        if (product == null) {
            new Alert(Alert.AlertType.ERROR,"No such Product").show();
        }else{
            lblProductIdRemove.setText(String.valueOf(product.getProductId()));
            lblProductName.setText(product.getName());
            lblProductDesc.setText(product.getDescription());
            lblProductCategory.setText(product.getCategory());
            lblUnitPrice.setText(String.valueOf(product.getPrice()));
            lblStockQuantity.setText(String.valueOf(product.getStockQuantity()));
            lblProductImageLink.setText(product.getProductImageLink());
        }
    }

    private Product searchProduct(String productId) {
        return searchedProduct = ProductController.getInstance().getProduct(productId);
    }

    public void btnRefreshRemoveProductOnAction(ActionEvent actionEvent) {
        refreshProcessProductRemove();
    }

    private void refreshProcessProductRemove(){
        txtProductIdRemove.clear();
        lblProductIdRemove.setText(null);
        lblProductName.setText(null);
        lblProductDesc.setText(null);
        lblProductCategory.setText(null);
        lblUnitPrice.setText(null);
        lblStockQuantity.setText(null);
        lblProductImageLink.setText(null);
    }

    public void txtProductIdRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveProductData();
    }

    public void btnRemoveProductOnAction(ActionEvent actionEvent) {
        boolean isRemoved = ProductController.getInstance().removeProduct(txtProductIdRemove.getText());
        if (isRemoved){
            new Alert(Alert.AlertType.CONFIRMATION,"Removed the Product").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while removing the Product").show();
        }
        refreshProcessProductRemove();
    }
}
