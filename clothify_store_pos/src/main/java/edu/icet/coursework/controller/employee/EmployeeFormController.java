package edu.icet.coursework.controller.employee;

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
    private User loggedInUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            lblEmployeeId.setText(String.valueOf(loggedInUser.getUserId()));
            lblEmployeeName.setText(loggedInUser.getName());
        });

    }


    public void initUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
