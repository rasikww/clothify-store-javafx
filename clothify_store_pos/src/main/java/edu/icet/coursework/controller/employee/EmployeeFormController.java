package edu.icet.coursework.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.controller.customer.CustomerController;
import edu.icet.coursework.controller.order.OrderController;
import edu.icet.coursework.controller.product.ProductController;
import edu.icet.coursework.controller.receipt.ReceiptController;
import edu.icet.coursework.controller.report.ReportController;
import edu.icet.coursework.controller.supplier.SupplierController;
import edu.icet.coursework.dto.*;
import edu.icet.coursework.util.ReportType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeFormController implements Initializable {
    
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
    public JFXButton btnSearchProductUpdate;
    public JFXTextField txtProductNameUpdate;
    public JFXTextArea txtProductDescUpdate;
    public JFXTextField txtUnitPriceUpdate;
    public JFXTextField txtStockQuantityUpdate;
    public JFXTextField txtProductImageLinkUpdate;
    public Label lblProductIdUpdate;
    public ComboBox<String> cmbProductCategoryUpdate;
    public ComboBox<String> cmbSupplierUpdateProduct;
    public Label lblSupplierNameUpdateProduct;
    public Label lblSupplierCompanyUpdateProduct;
    public Label lblSupplierEmailUpdateProduct;
    public Label lblSupplierPhoneNoUpdateProduct;
    public JFXButton btnUpdateProduct;
    public JFXButton btnRefreshUpdateProduct;
    public JFXTextField txtProductIdUpdate;
    public Tab tabUpdateProduct;
    public TableColumn<Product, Integer> colProductId;
    public TableColumn<Product, Integer> colSupplierIdProductTable;
    public TableColumn<Product, String> colProductName;
    public TableColumn<Product, String> colProductDesc;
    public TableColumn<Product, Double> colUnitPrice;
    public TableColumn<Product, Integer> colStockQty;
    public TableColumn<Product, String> colCategory;
    public TableView<Product> tblProducts;
    public JFXButton btnRefreshTblProducts;
    public JFXButton btnRefreshTblSuppliers;
    public ComboBox<String> cmbCustomerPlaceOrder;
    public ComboBox<String> cmbProductPlaceOrder;
    public Tab tabOrders;
    public Label lblCustomerNamePlace;
    public Label lblCustomerEmailPlace;
    public Label lblCustomerPhoneNoPlace;
    public JFXTextField txtRequiredQty;
    public Label lblProductDescPlace;
    public Label lblUnitPricePlace;
    public Label lblStockQtyPlace;
    public TableView<OrderDetail> tblCart;
    public TableColumn<OrderDetail,Integer> colProductIdCart;
    public TableColumn<OrderDetail, String> colProductNameCart;
    public TableColumn<OrderDetail, Double> colUnitPriceCart;
    public TableColumn<OrderDetail, Integer> colQuantityCart;
    public TableColumn<OrderDetail, Double> colTotalCart;
    public JFXButton btnAddToCart;
    public Label lblDescSelected;
    public Label lblUnitPriceSelected;
    public Label lblQtySelected;
    public Label lblRequiredQtySelected;
    public JFXButton btnRemoveSelected;
    public JFXButton btnRemoveAll;
    public JFXButton btnPlaceOrder;
    public Label lblTotal;
    public Label lblCurrentDate;
    public Label lblCurrentTime;
    public Label lblOrderIdPlace;
    public Label lblCustomerIdPlace;
    public Label lblCustomerNamePlace2;
    public Label lblOrderDate;
    public Label lblOrderTime;
    public Label lblOrderTotal;
    public Label lblOrderPlaced;
    public JFXButton btnRefreshPlace;
    public Label lblUserName;
    public Label lblUserId;
    public Label lblOrderIdRemove;
    public Label lblCustomerIdRemoveOrder;
    public Label lblCustomerNameRemoveOrder;
    public Label lblDateRemoveOrder;
    public Label lblTimeRemoveOrder;
    public JFXButton btnRemoveOrder;
    public JFXButton btnRefreshRemoveOrder;
    public JFXTextField txtOrderIdRemove;
    public JFXButton btnSearchOrderRemove;
    public Label lblTotalRemoveOrder;
    public JFXButton btnUpdateOrder;
    public JFXButton btnRefreshUpdateOrder;
    public JFXTextField txtOrderIdUpdate;
    public JFXButton btnSearchOrderUpdate;
    public Label lblOrderIdUpdate;
    public Label lblCustomerNameUpdateOrder;
    public Label lblDateUpdateOrder;
    public Label lblTimeUpdateOrder;
    public Label lblTotalUpdateOrder;
    public ComboBox<String> cmbCustomerUpdateOrder;
    public Tab tabUpdateOrder;
    public Tab tabViewOrders;
    public TableColumn<Order, Integer> colOrderId;
    public TableColumn<Order, Integer> colCustomerIdOrder;
    public TableColumn<Order, Integer> colUserIdOrder;
    public TableColumn<Order, LocalDateTime> colDateTime;
    public TableColumn<Order, Double> colTotalCost;
    public JFXButton btnRefreshTblOrders;
    public TableView<Order> tblOrders;
    public JFXButton btnGenerateCatalog;
    public JFXButton btnGenerateQtySold;
    public JFXButton btnEmployeeDetailsReport;
    public JFXButton btnSupplierDetailsReport;
    private User loggedInUser;
    private String nextCustomerId;
    private String nextSupplierId;
    private String nextProductId;
    private String nextOrderId;
    private Customer searchedCustomer;
    private Supplier searchedSupplier;
    private Product searchedProduct;
    private Customer selectedCustomer;
    private Product selectedProduct;
    private ObservableList<OrderDetail> cartTableList = FXCollections.observableArrayList();
    private OrderDetail selectedOrderDetail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            lblUserId.setText(String.valueOf(loggedInUser.getUserId()));
            lblUserName.setText(loggedInUser.getName());
        });
        displayNextCustomerId();
        displayNextSupplierId();
        loadDateAndTime();
        tblCart.getFocusModel().focusedIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            if (newIndex != null && newIndex.intValue() >= 0) {
                // Get the selected item at the new focused index
                selectedOrderDetail = tblCart.getItems().get(newIndex.intValue());
                if (selectedOrderDetail != null) {
                    showSelectedProductDetail(selectedOrderDetail);
                    btnRemoveSelected.setDisable(false);
                }
            }
        });
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblCurrentDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> {
            LocalTime time = LocalTime.now();
            lblCurrentTime.setText(
                    String.format("%02d : %02d : %02d", time.getHour(), time.getMinute(), time.getSecond())
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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

    public void btnRefreshTblSuppliersOnAction(ActionEvent actionEvent) {
        loadSuppliersTable();
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
        cmbSupplierAddProduct.getSelectionModel().select(0);
        cmbCategoryAddProduct.getSelectionModel().select(0);
        displayNextProductId();
        txtProductNameAddProduct.clear();
        txtProductDescAddProduct.clear();
        txtProductUnitPriceAddProduct.clear();
        txtStockQtyAddProduct.clear();
        txtImageLinkAddProduct.clear();
    }
    public void tabProductsOnChanged(Event event) {
        displayNextProductId();
        loadComboBoxSupplier(cmbSupplierAddProduct);
        loadComboBoxProductCategory(cmbCategoryAddProduct);

        loadProductsTable();
    }

    private void loadProductsTable() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSupplierIdProductTable.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStockQty.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        ObservableList<Product> allProducts = ProductController.getInstance().getAllProducts();
        tblProducts.setItems(allProducts);
    }

    private void displayNextProductId() {
        nextProductId = getNextProductId();
        lblProductIdAddProduct.setText(nextProductId);
    }

    private String getNextProductId() {
        return ProductController.getInstance().generateNextProductId();
    }

    private void loadComboBoxProductCategory(ComboBox<String> comboBox) {
        ObservableList<String> categories = FXCollections.observableArrayList("Ladies", "Gents", "Kids");
        comboBox.setItems(categories);
    }

    private void loadComboBoxSupplier(ComboBox<String> comboBox) {
        ObservableList<Supplier> allSuppliers = SupplierController.getInstance().getAllSuppliers();
        ObservableList<String> dropDownItems = FXCollections.observableArrayList();
        allSuppliers.forEach(supplier -> {
            dropDownItems.add(String.format("%d - %s",supplier.getSupplierId(),supplier.getName()));
        });
        comboBox.setItems(dropDownItems);
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

    public void cmbSupplierUpdateProductOnAction(ActionEvent actionEvent) {
        int index = cmbSupplierUpdateProduct.getSelectionModel().getSelectedIndex();
        Supplier supplier = SupplierController.getInstance().getAllSuppliers().get(index);
        displaySupplierDataUpdateProduct(supplier);
    }

    public void btnUpdateProductOnAction(ActionEvent actionEvent) {
        String productName = txtProductNameUpdate.getText();
        String productDesc = txtProductDescUpdate.getText();
        String productCategory = cmbProductCategoryUpdate.getSelectionModel().getSelectedItem();
        Double unitPrice = Double.valueOf(txtUnitPriceUpdate.getText());
        Integer stockQty = Integer.valueOf(txtStockQuantityUpdate.getText());
        String link = txtProductImageLinkUpdate.getText();
        Integer supplierId = SupplierController
                .getInstance()
                .getAllSuppliers()
                .get(cmbSupplierUpdateProduct.getSelectionModel().getSelectedIndex())
                .getSupplierId();

        Product product = new Product(
                searchedProduct.getProductId(),
                supplierId,
                productName,
                productDesc,
                unitPrice,
                stockQty,
                link,
                productCategory
        );

        boolean isUpdated = ProductController.getInstance().updateProduct(product);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated the product").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while updating the product").show();
        }
        refreshProcessUpdateProduct();
    }

    public void btnRefreshUpdateProductOnAction(ActionEvent actionEvent) {
        refreshProcessUpdateProduct();
    }

    public void txtProductIdUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateProductData();
    }

    public void btnSearchProductUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateProductData();
    }

    private void displayUpdateProductData() {
        var product = searchProduct(txtProductIdUpdate.getText());
        if (product == null) {
            new Alert(Alert.AlertType.ERROR,"No such Product").show();
        }else{
            lblProductIdUpdate.setText(String.valueOf(product.getProductId()));
            txtProductNameUpdate.setText(product.getName());
            txtProductDescUpdate.setText(product.getDescription());
            cmbProductCategoryUpdate.getSelectionModel().select(product.getCategory());
            txtUnitPriceUpdate.setText(String.valueOf(product.getPrice()));
            txtStockQuantityUpdate.setText(String.valueOf(product.getStockQuantity()));
            txtProductImageLinkUpdate.setText(product.getProductImageLink());
            Supplier supplier = searchSupplier(String.valueOf(product.getSupplierId()));
            int index = SupplierController.getInstance().getAllSuppliers().indexOf(supplier);
            cmbSupplierUpdateProduct.getSelectionModel().select(index);
            displaySupplierDataUpdateProduct(supplier);
        }
    }
    private void displaySupplierDataUpdateProduct(Supplier supplier){
        lblSupplierNameUpdateProduct.setText(supplier.getName());
        lblSupplierCompanyUpdateProduct.setText(supplier.getCompany());
        lblSupplierEmailUpdateProduct.setText(supplier.getEmail());
        lblSupplierPhoneNoUpdateProduct.setText(supplier.getPhoneNumber());
    }

    public void tabUpdateProductOnChanged(Event event) {
        loadComboBoxSupplier(cmbSupplierUpdateProduct);
        loadComboBoxProductCategory(cmbProductCategoryUpdate);
    }
    private void refreshProcessUpdateProduct(){
        txtProductIdUpdate.clear();
        lblProductIdUpdate.setText(null);
        txtProductNameUpdate.setText(null);
        txtProductDescUpdate.setText(null);
        cmbProductCategoryUpdate.getSelectionModel().select(0);
        txtUnitPriceUpdate.setText(null);
        txtStockQuantityUpdate.setText(null);
        txtProductImageLinkUpdate.setText(null);
        cmbSupplierUpdateProduct.getSelectionModel().select(0);
        lblSupplierNameUpdateProduct.setText(null);
        lblSupplierCompanyUpdateProduct.setText(null);
        lblSupplierEmailUpdateProduct.setText(null);
        lblSupplierPhoneNoUpdateProduct.setText(null);
    }

    public void btnRefreshTblProductsOnAction(ActionEvent actionEvent) {
        loadProductsTable();
    }
//----------------------------------------------------------------------Order section------------
    public void cmbCustomerPlaceOrderOnAction(ActionEvent actionEvent) {
        int selectedIndex = cmbCustomerPlaceOrder.getSelectionModel().getSelectedIndex();
        selectedCustomer = CustomerController.getInstance().getAllCustomers().get(selectedIndex);
        lblCustomerNamePlace.setText(selectedCustomer.getName());
        lblCustomerEmailPlace.setText(selectedCustomer.getEmail());
        lblCustomerPhoneNoPlace.setText(selectedCustomer.getPhoneNumber());
        lblCustomerIdPlace.setText(String.valueOf(selectedCustomer.getCustomerId()));
        lblCustomerNamePlace2.setText(selectedCustomer.getName());
        cmbProductPlaceOrder.setDisable(false);
    }

    public void cmbProductPlaceOrderOnAction(ActionEvent actionEvent) {
        int selectedIndex = cmbProductPlaceOrder.getSelectionModel().getSelectedIndex();
        selectedProduct = ProductController.getInstance().getAllProducts().get(selectedIndex);
        lblProductDescPlace.setText(selectedProduct.getDescription());
        lblUnitPricePlace.setText(String.valueOf(selectedProduct.getPrice()));
        lblStockQtyPlace.setText(String.valueOf(selectedProduct.getStockQuantity()));
        cmbCustomerPlaceOrder.setDisable(true);
    }

    public void tabOrdersOnChanged(Event event) {
        loadComboBoxCustomer(cmbCustomerPlaceOrder);
        loadComboBoxProduct();
        loadTblCart();
        displayNextOrderId();
        cmbProductPlaceOrder.setDisable(true);
        btnRemoveSelected.setDisable(true);
        btnRemoveAll.setDisable(true);
        btnPlaceOrder.setDisable(true);
    }

    private void displayNextOrderId() {
        nextOrderId = getNextOrderId();
        lblOrderIdPlace.setText(nextOrderId);
    }

    private String getNextOrderId() {
        return OrderController.getInstance().generateNextOrderId();
    }

    private void loadTblCart() {
        colProductIdCart.setCellValueFactory(tf -> {
            ObservableValue<Integer> value = new SimpleIntegerProperty(tf.getValue().getProduct().getProductId()).asObject();
            return value;
        });
        colProductNameCart.setCellValueFactory(tf -> {
            ObservableValue<String> value = new SimpleStringProperty(tf.getValue().getProduct().getName());
            return value;
        });
        colUnitPriceCart.setCellValueFactory(tf -> {
            ObservableValue<Double> value = new SimpleDoubleProperty(tf.getValue().getProduct().getPrice()).asObject();
            return value;
        });
        colQuantityCart.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colTotalCart.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void loadComboBoxProduct() {
        ObservableList<Product> allProducts = ProductController.getInstance().getAllProducts();
        ObservableList<String> dropDownItems = FXCollections.observableArrayList();
        allProducts.forEach(product -> {
            dropDownItems.add(String.format("%d - %s",product.getProductId(),product.getName()));
        });
        cmbProductPlaceOrder.setItems(dropDownItems);
    }

    private void loadComboBoxCustomer(ComboBox<String> comboBox) {
        ObservableList<Customer> allCustomers = CustomerController.getInstance().getAllCustomers();
        ObservableList<String> dropDownItems = FXCollections.observableArrayList();
        allCustomers.forEach(customer -> {
            dropDownItems.add(String.format("%d - %s",customer.getCustomerId(),customer.getName()));
        });
        comboBox.setItems(dropDownItems);
    }

    public void txtRequiredQtyOnAction(ActionEvent actionEvent) {
        addToCartProcess();
    }

    private void addToCartProcess() {
        int selectedIndex = cmbProductPlaceOrder.getSelectionModel().getSelectedIndex();
        Product product = ProductController.getInstance().getAllProducts().get(selectedIndex);
        Pattern pattern = Pattern.compile("\\d+");
        String requiredQty = txtRequiredQty.getText();
        if (!pattern.matcher(requiredQty).matches()){
           new Alert(Alert.AlertType.ERROR,"Enter a Number").show();
        } else if (product.getStockQuantity() < Integer.parseInt(requiredQty) || Integer.parseInt(requiredQty)<=0) {
            new Alert(Alert.AlertType.ERROR,"Cannot provide that much").show();
        }else{
            OrderDetail orderDetail = new OrderDetail(
                    Integer.parseInt(requiredQty),
                    null,
                    selectedProduct,
                    selectedProduct.getPrice() * Integer.parseInt(requiredQty)
            );
            cartTableList.add(orderDetail);
            tblCart.setItems(cartTableList);
            lblTotal.setText(String.valueOf(calculateTotal()));
            btnRemoveAll.setDisable(false);
            btnPlaceOrder.setDisable(false);
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        addToCartProcess();
    }

    private void showSelectedProductDetail(OrderDetail orderDetail) {
        if (orderDetail!= null) {
            lblDescSelected.setText(orderDetail.getProduct().getDescription());
            lblUnitPriceSelected.setText(String.valueOf(orderDetail.getProduct().getPrice()));
            lblQtySelected.setText(String.valueOf(orderDetail.getProduct().getStockQuantity()));
            lblRequiredQtySelected.setText(String.valueOf(orderDetail.getQuantity()));
            btnRemoveSelected.setDisable(false);
        }
    }

    public void btnRemoveSelectedOnAction(ActionEvent actionEvent) {
        boolean isRemoved = cartTableList.remove(selectedOrderDetail);
        if (isRemoved) {
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Product: "+
                            selectedOrderDetail.getProduct().getName()+
                            ", Removed from cart").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Cannot remove the product").show();
        }
        clearSelectedProductDetail();
        btnRemoveSelected.setDisable(true);
        if (cartTableList.isEmpty()){
            lblTotal.setText(null);
            btnRemoveAll.setDisable(true);
            btnPlaceOrder.setDisable(true);
        }else {
            lblTotal.setText(String.valueOf(calculateTotal()));
        }
    }

    private void clearSelectedProductDetail() {
        lblDescSelected.setText(null);
        lblUnitPriceSelected.setText(null);
        lblQtySelected.setText(null);
        lblRequiredQtySelected.setText(null);
        btnRemoveSelected.setDisable(false);
    }

    private Double calculateTotal() {
        Double total = 0.0;
        for (OrderDetail orderDetail : cartTableList) {
            total += orderDetail.getTotalPrice();
        }
        return new BigDecimal(total)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public void tblCartOnMouseClick(MouseEvent mouseEvent) {
        if (selectedOrderDetail!= null) {
            selectedOrderDetail = tblCart.getSelectionModel().getSelectedItem();
            showSelectedProductDetail(selectedOrderDetail);
        }
    }

    public void btnRemoveAllOnAction(ActionEvent actionEvent) {
        cartTableList.clear();
        new Alert(Alert.AlertType.CONFIRMATION, "Removed all products from the cart").show();
        lblTotal.setText(null);
        clearSelectedProductDetail();
        btnRemoveAll.setDisable(true);
        btnRemoveSelected.setDisable(true);
        btnPlaceOrder.setDisable(true);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        tblCart.setDisable(true);
        btnRemoveSelected.setDisable(true);
        btnRemoveAll.setDisable(true);
        cmbProductPlaceOrder.setDisable(true);
        txtRequiredQty.setDisable(true);
        btnAddToCart.setDisable(true);
        btnPlaceOrder.setDisable(true);
        lblOrderDate.setText(lblCurrentDate.getText());
        lblOrderTime.setText(lblCurrentTime.getText());
        lblOrderTotal.setText(lblTotal.getText());
        lblOrderPlaced.setVisible(true);

        orderPlacingProcess();


    }

    private void orderPlacingProcess() {
        List<OrderDetail> orderDetails = cartTableList;
        Order order = new Order(
                Integer.parseInt(getNextOrderId()),
                selectedCustomer.getCustomerId(),
                LocalDateTime.now(),
                Double.parseDouble(lblOrderTotal.getText()),
                orderDetails,
                loggedInUser.getUserId()
        );

        boolean isAdded = OrderController.getInstance().addOrder(order);
        if(isAdded){
            afterPlacingOrder();
            new Alert(Alert.AlertType.CONFIRMATION,"Order Placed").show();
            viewReceipt();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can't Place the Order").show();
        }
    }

    public void btnRefreshPlaceOnAction(ActionEvent actionEvent) {
        refreshProcessPlaceOrder();
    }
    private void afterPlacingOrder(){
        cartTableList.clear();
        tblCart.setDisable(false);
        btnRemoveSelected.setDisable(true);
        btnRemoveAll.setDisable(true);
        cmbProductPlaceOrder.setDisable(true);
        txtRequiredQty.setDisable(true);
        btnAddToCart.setDisable(true);
        btnPlaceOrder.setDisable(true);
        btnRemoveAll.setDisable(true);
        btnRemoveSelected.setDisable(true);
        cmbCustomerPlaceOrder.setDisable(true);
    }

    private void viewReceipt() {
        boolean isViewable  = ReceiptController.getInstance().viewLastReceipt();
        if(!isViewable){
            new Alert(Alert.AlertType.ERROR,"Error Occurred while Viewing Receipt").show();
        }
    }

    private void refreshProcessPlaceOrder(){
        cartTableList.clear();
        tblCart.setDisable(false);
        btnRemoveSelected.setDisable(true);
        btnRemoveAll.setDisable(true);
        cmbProductPlaceOrder.getSelectionModel().select(0);
        cmbProductPlaceOrder.setDisable(true);
        txtRequiredQty.clear();
        txtRequiredQty.setDisable(false);
        btnAddToCart.setDisable(false);
        btnPlaceOrder.setDisable(true);
        btnRemoveAll.setDisable(true);
        btnRemoveSelected.setDisable(true);
        lblOrderDate.setText(null);
        lblOrderTime.setText(null);
        lblOrderTotal.setText(null);
        lblOrderPlaced.setVisible(false);
        cmbCustomerPlaceOrder.setDisable(false);
        cmbCustomerPlaceOrder.getSelectionModel().select(0);
        displayNextOrderId();
    }

    public void btnRemoveOrderOnAction(ActionEvent actionEvent) {
        boolean isRemoved = OrderController.getInstance().removeOrder(txtOrderIdRemove.getText());
        if (isRemoved){
            new Alert(Alert.AlertType.CONFIRMATION,"Removed the Order").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while removing the Order").show();
        }
        refreshProcessOrderRemove();
    }

    private void refreshProcessOrderRemove() {
        txtOrderIdRemove.clear();
        lblOrderIdRemove.setText(null);
        lblCustomerIdRemoveOrder.setText(null);
        lblCustomerNameRemoveOrder.setText(null);
        lblDateRemoveOrder.setText(null);
        lblTimeRemoveOrder.setText(null);
        lblTotalRemoveOrder.setText(null);
    }

    public void btnRefreshRemoveOrderOnAction(ActionEvent actionEvent) {
        refreshProcessOrderRemove();
    }

    public void txtOrderIdRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveOrderData();
    }

    public void btnSearchOrderRemoveOnAction(ActionEvent actionEvent) {
        displayRemoveOrderData();
    }

    private void displayRemoveOrderData() {
        Order order = searchOrder(txtOrderIdRemove.getText());
        if (order == null) {
            new Alert(Alert.AlertType.ERROR,"No such Order").show();
        }else{
            lblOrderIdRemove.setText(String.valueOf(order.getOrderId()));
            lblCustomerIdRemoveOrder.setText(String.valueOf(order.getCustomerId()));
            Customer customer = searchCustomer(String.valueOf(order.getCustomerId()));
            lblCustomerNameRemoveOrder.setText(customer.getName());
            LocalDateTime orderDateTime = order.getOrderDateTime();
            lblDateRemoveOrder.setText(String.valueOf(orderDateTime.toLocalDate()));
            lblTimeRemoveOrder.setText(String.valueOf(orderDateTime.toLocalTime()).split("\\.")[0]);
            lblTotalRemoveOrder.setText(String.valueOf(order.getTotalCost()));
        }
    }

    private Order searchOrder(String orderId) {
        return OrderController.getInstance().getOrder(orderId);
    }

    public void btnUpdateOrderOnAction(ActionEvent actionEvent) {
        Integer customerId = Integer.valueOf(getSelectedCustomer()[0]);

        Order order = new Order();
        order.setOrderId(Integer.valueOf(lblOrderIdUpdate.getText()));
        order.setCustomerId(customerId);

        boolean isUpdated = OrderController.getInstance().updateOrder(order);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated the order").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while updating the order").show();
        }
        refreshProcessOrderUpdate();
    }

    private void refreshProcessOrderUpdate() {
        txtOrderIdUpdate.clear();
        lblOrderIdUpdate.setText(null);
        cmbCustomerUpdateOrder.getSelectionModel().select(0);
        lblCustomerNameUpdateOrder.setText(null);
        lblDateUpdateOrder.setText(null);
        lblTimeUpdateOrder.setText(null);
        lblTotalUpdateOrder.setText(null);
    }

    public void btnRefreshUpdateOrderOnAction(ActionEvent actionEvent) {
        refreshProcessOrderUpdate();
    }

    public void txtOrderIdUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateOrderData();
    }

    public void btnSearchOrderUpdateOnAction(ActionEvent actionEvent) {
        displayUpdateOrderData();
    }

    private void displayUpdateOrderData() {
        Order order = searchOrder(txtOrderIdUpdate.getText());
        if (order == null) {
            new Alert(Alert.AlertType.ERROR,"No such Order").show();
        }else{
            lblOrderIdUpdate.setText(String.valueOf(order.getOrderId()));
            Customer customer = searchCustomer(String.valueOf(order.getCustomerId()));
            lblCustomerNameUpdateOrder.setText(customer.getName());
            LocalDateTime orderDateTime = order.getOrderDateTime();
            lblDateUpdateOrder.setText(String.valueOf(orderDateTime.toLocalDate()));
            lblTimeUpdateOrder.setText(String.valueOf(orderDateTime.toLocalTime()).split("\\.")[0]);
            lblTotalUpdateOrder.setText(String.valueOf(order.getTotalCost()));
        }
    }

    public void cmbCustomerUpdateOrderOnAction(ActionEvent actionEvent) {
        lblCustomerNameUpdateOrder.setText(getSelectedCustomer()[1]);
    }

    private String[] getSelectedCustomer(){
        return cmbCustomerUpdateOrder
                .getSelectionModel()
                .getSelectedItem()
                .split(" - ");
    }

    public void tabUpdateOrderOnChanged(Event event) {
        loadComboBoxCustomer(cmbCustomerUpdateOrder);
    }

    public void tabViewOrdersOnAction(Event event) {
        loadTblOrders();
    }

    public void btnRefreshTblOrdersOnAction(ActionEvent actionEvent) {
        loadTblOrders();
    }

    private void loadTblOrders() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerIdOrder.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colUserIdOrder.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colDateTime.setCellValueFactory(new PropertyValueFactory<>("orderDateTime"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        ObservableList<Order> allOrders = OrderController.getInstance().getAllOrders();
        tblOrders.setItems(allOrders);
    }

    public void btnGenerateCatalogOnAction(ActionEvent actionEvent) {
        generateReport(loggedInUser,ReportType.PRODUCT_CATALOG);
    }

    public void btnGenerateQtySoldOnAction(ActionEvent actionEvent) {
        generateReport(loggedInUser,ReportType.QTY_SOLD_PER_PRODUCT);
    }
    private void generateReport(User loggedInUser, ReportType reportType){
        Report report = new Report();
        report.setUser(loggedInUser);
        report.setReportType(reportType);
        report.setReportDateTime(LocalDateTime.now());
        System.out.println("manual report: "+report);
        boolean isGenerated = ReportController.getInstance().generateReport(report);
        if (!isGenerated){
            new Alert(Alert.AlertType.ERROR,"Error Occurred while generating the report").show();
        }
    }

    public void btnEmployeeDetailsReportOnAction(ActionEvent actionEvent) {
        generateReport(loggedInUser,ReportType.ALL_USERS_REPORT);
    }

    public void btnSupplierDetailsReportOnAction(ActionEvent actionEvent) {
        generateReport(loggedInUser,ReportType.ALL_SUPPLIERS_REPORT);
    }
}
