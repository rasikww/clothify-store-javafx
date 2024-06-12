package edu.icet.coursework.controller.admin;

import edu.icet.coursework.controller.employee.EmployeeFormController;
import edu.icet.coursework.dto.User;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminFormController implements Initializable {
    public Label lblLogout;
    public Label lblAdminName;
    public Label lblAdminId;
    private User loggedInUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            lblAdminId.setText(String.valueOf(loggedInUser.getUserId()));
            lblAdminName.setText(loggedInUser.getName());
        });
    }
    public void initUser(User loggedInUser){
        this.loggedInUser = loggedInUser;
    }

    public void lblLogoutOnMouseClick(MouseEvent mouseEvent) {
    }


}
