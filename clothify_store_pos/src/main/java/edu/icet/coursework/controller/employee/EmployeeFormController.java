package edu.icet.coursework.controller.employee;

import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.controller.customer.CustomerController;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.dto.User;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private User loggedInUser;
    private String nextCustomerId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            lblEmployeeId.setText(String.valueOf(loggedInUser.getUserId()));
            lblEmployeeName.setText(loggedInUser.getName());
        });
        nextCustomerId = getNextCustomerId();
        lblCustomerId.setText(nextCustomerId);
    }

    private String getNextCustomerId() {
        return CustomerController.getInstance().generateNextCustomerId();
    }


    public void initUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
