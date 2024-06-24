package edu.icet.coursework.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.controller.employee.EmployeeFormController;
import edu.icet.coursework.controller.user.UserController;
import edu.icet.coursework.dto.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminFormController extends EmployeeFormController {

    public Tab tabAccounts;
    public TableView<User> tblUsers;
    public TableColumn<User, Integer> colUserId;
    public TableColumn<User, String> colAccountType;
    public TableColumn<User, String> colUserName;
    public TableColumn<User, String> colUserEmail;
    public TableColumn<User, String> colUserPhoneNo;
    public JFXButton btnRefreshTblUsers;
    public ComboBox<String> cmbAccountType;
    public Label lblUseIdAddAccount;
    public JFXTextField txtUserNameAdd;
    public JFXTextField txtUserEmailAdd;
    public JFXTextField txtUserPhoneNoAdd;
    public JFXButton btnAddUser;
    public JFXButton btnRefreshAddUser;
    public JFXTextField txtUserIdRemove;
    public JFXButton btnSearchUserRemove;
    public Label lblUserIdRemove;
    public Label lblAccountTypeRemove;
    public Label lblUserNameRemove;
    public Label lblUserEmailRemove;
    public Label lblUserPhoneNoRemove;
    public JFXButton btnRemoveUser;
    public JFXButton btnRefreshUserRemove;
    public JFXTextField txtUserNameUpdate;
    public JFXTextField txtUserEmailUpdate;
    public JFXTextField txtUserPhoneNoUpdate;
    public ComboBox<String> cmbAccountTypeUpdate;
    public Label lblUserIdUpdate;
    public JFXButton btnUpdateUser;
    public JFXButton btnRefreshUpdateUser;
    public JFXTextField txtUserIdUpdate;
    public JFXButton btnSearchUpdateUser;
    public Tab tabViewUsers;
    private String nextUserId;
    private User searchedUser;

    public void tabAccountsOnAction(Event event) {
        loadTblUsers();
        loadAllCmbAccountType();
        displayNextUserId();
    }

    private void displayNextUserId() {
        nextUserId = UserController.getInstance().generateNextUserId();
        lblUseIdAddAccount.setText(nextUserId);
    }

    private void loadAllCmbAccountType() {
        ObservableList<String> accountType = FXCollections.observableArrayList("employee","admin");
        cmbAccountType.setItems(accountType);
        cmbAccountTypeUpdate.setItems(accountType);
    }

    private void loadTblUsers() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAccountType.setCellValueFactory(cellData -> {
            boolean isAdmin = cellData.getValue().getIsAdmin();
            ObservableValue<String> value = new SimpleStringProperty(isAdmin ? "admin" : "employee");
            return value;
        });
        ObservableList<User> allUsers = UserController.getInstance().getAllUsers();
        tblUsers.setItems(allUsers);
    }

    public void btnRefreshTblUsersOnAction(ActionEvent actionEvent) {
        loadTblUsers();
    }

    public void cmbAccountTypeOnAction(ActionEvent actionEvent) {
        txtUserNameAdd.requestFocus();
    }

    public void txtUserNameAddOnAction(ActionEvent actionEvent) {
        txtUserEmailAdd.requestFocus();
    }

    public void txtUserEmailAddOnAction(ActionEvent actionEvent) {
        txtUserPhoneNoAdd.requestFocus();
    }

    public void txtUserPhoneNoAddOnAction(ActionEvent actionEvent) {
        addUserProcess();
    }

    private void addUserProcess() {
        User user = new User();
        user.setUserId(Integer.valueOf(nextUserId));
        user.setName(txtUserNameAdd.getText());
        user.setPhoneNumber(txtUserPhoneNoAdd.getText());
        user.setEmail(txtUserEmailAdd.getText());
        int selectedIndex = cmbAccountType.getSelectionModel().getSelectedIndex();
        user.setIsAdmin(selectedIndex == 1);

        boolean isAdded = UserController.getInstance().addUser(user);
        if(isAdded){
            refreshAddUserProcess();
            new Alert(Alert.AlertType.CONFIRMATION,"User Added").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can't add User").show();
        }

    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {
        addUserProcess();
    }

    public void btnRefreshAddUserOnAction(ActionEvent actionEvent) {
        refreshAddUserProcess();
    }

    private void refreshAddUserProcess() {
        cmbAccountType.getSelectionModel().select(0);
        displayNextUserId();
        txtUserNameAdd.clear();
        txtUserEmailAdd.clear();
        txtUserPhoneNoAdd.clear();
    }

    public void txtUserIdRemoveOnAction(ActionEvent actionEvent) {
        displayUserRemoveData();
    }

    public void btnSearchUserRemoveOnAction(ActionEvent actionEvent) {
        displayUserRemoveData();
    }

    private void displayUserRemoveData() {
        User user = searchUser(txtUserIdRemove.getText());
        if (user == null) {
            new Alert(Alert.AlertType.ERROR,"No such User").show();
        }else{
            lblUserIdRemove.setText(String.valueOf(user.getUserId()));
            lblUserNameRemove.setText(user.getName());
            lblUserPhoneNoRemove.setText(user.getPhoneNumber());
            lblUserEmailRemove.setText(user.getEmail());
            lblAccountTypeRemove.setText(user.getIsAdmin() ? "admin":"employee");
        }
    }

    private User searchUser(String userId){
        return searchedUser = UserController.getInstance().getUser(userId);
    }

    public void btnRemoveUserOnAction(ActionEvent actionEvent) {
        boolean isRemoved = UserController.getInstance().removeUser(txtUserIdRemove.getText());
        if (isRemoved){
            new Alert(Alert.AlertType.CONFIRMATION,"Removed the user").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while removing the user").show();
        }
        refreshProcessUserRemove();
    }

    public void btnRefreshUserRemoveOnAction(ActionEvent actionEvent) {
        refreshProcessUserRemove();
    }

    private void refreshProcessUserRemove() {
        txtUserIdRemove.clear();
        lblUserIdRemove.setText(null);
        lblUserNameRemove.setText(null);
        lblUserPhoneNoRemove.setText(null);
        lblUserEmailRemove.setText(null);
        lblAccountTypeRemove.setText(null);
    }

    public void txtUserNameUpdateOnAction(ActionEvent actionEvent) {
        txtUserEmailUpdate.requestFocus();
    }

    public void txtUserEmailUpdateOnAction(ActionEvent actionEvent) {
        txtUserPhoneNoUpdate.requestFocus();
    }

    public void txtUserPhoneNoUpdateOnAction(ActionEvent actionEvent) {
        btnUpdateUser.requestFocus();
    }

    public void cmbAccountTypeUpdateOnAction(ActionEvent actionEvent) {
        txtUserNameUpdate.requestFocus();
    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
        User user = new User();
        user.setUserId(Integer.valueOf(lblUserIdUpdate.getText()));
        user.setName(txtUserNameUpdate.getText());
        user.setPhoneNumber(txtUserPhoneNoUpdate.getText());
        user.setEmail(txtUserEmailUpdate.getText());
        user.setIsAdmin(cmbAccountTypeUpdate.getSelectionModel().getSelectedIndex() == 1);

        boolean isUpdated = UserController.getInstance().updateUser(user);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated the user").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occurred while updating the user").show();
        }
        refreshProcessUserUpdate();
    }

    public void btnRefreshUpdateUserOnAction(ActionEvent actionEvent) {
        refreshProcessUserUpdate();
    }

    private void refreshProcessUserUpdate() {
        txtUserIdUpdate.clear();
        cmbAccountTypeUpdate.getSelectionModel().select(0);
        lblUserIdUpdate.setText(null);
        txtUserNameUpdate.clear();
        txtUserEmailUpdate.clear();
        txtUserPhoneNoUpdate.clear();
    }

    public void txtUserIdUpdateOnAction(ActionEvent actionEvent) {
        displayUserUpdateData();
        cmbAccountTypeUpdate.requestFocus();
    }

    private void displayUserUpdateData() {
        User user = searchUser(txtUserIdUpdate.getText());
        if (user == null) {
            new Alert(Alert.AlertType.ERROR,"No such User").show();
        }else{
            cmbAccountTypeUpdate.getSelectionModel().select(
                    (user.getIsAdmin()) ? 1 : 0 );
            lblUserIdUpdate.setText(String.valueOf(user.getUserId()));
            txtUserNameUpdate.setText(user.getName());
            txtUserEmailUpdate.setText(user.getEmail());
            txtUserPhoneNoUpdate.setText(user.getPhoneNumber());
        }
    }

    public void btnSearchUpdateUserOnAction(ActionEvent actionEvent) {
        displayUserUpdateData();
        cmbAccountTypeUpdate.requestFocus();
    }

    public void tabViewUsersOnChanged(Event event) {
        loadTblUsers();
    }
}
