package edu.icet.coursework.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenFormController implements Initializable {
    public JFXPasswordField txtPassword;
    public JFXTextField txtEmail;
    public Hyperlink linkChangePassword;
    public JFXButton btnLogin;
    public Button btnClose;
    private EmailValidator emailValidator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> txtEmail.requestFocus());
        emailValidator = EmailValidator.getInstance();
    }
    @FXML
    private void closeWindow(){
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void linkChangePasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String strEmail = txtEmail.getText();
        String strPassword = txtPassword.getText();

        if(!(emailValidator.isValid(strEmail))){
            new Alert(Alert.AlertType.ERROR,"Enter Valid Email Address").show();
        } else {
            boolean isUserExist = LoginController.getInstance().loginProcess(strEmail, strPassword);
            if (!isUserExist){
                new Alert(Alert.AlertType.ERROR,"Email and Password doesn't match").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Login Successful").show();
                closeWindow();
            }
        }
    }


    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }


}
