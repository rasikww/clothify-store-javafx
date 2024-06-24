package edu.icet.coursework.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.coursework.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
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
        Platform.runLater(() -> {
            txtEmail.requestFocus();
        });
        emailValidator = EmailValidator.getInstance();

    }
    @FXML
    private void closeWindow(){
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        btnLoginProcess();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void linkChangePasswordOnAction(ActionEvent actionEvent) {
        //closeWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_password_change.fxml"));
            Parent root = loader.load();

            LoginPasswordChangeFormController loginPasswordChangeFormController = loader.getController();

            Stage stage = Main.getPrimaryStage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            //stage.initStyle(StageStyle.UNDECORATED);
            //stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        btnLoginProcess();
    }

    private void btnLoginProcess(){
        String strEmail = txtEmail.getText();
        String strPassword = txtPassword.getText();

        if(!(emailValidator.isValid(strEmail))){
            new Alert(Alert.AlertType.ERROR,"Enter Valid Email Address").show();
        } else {
            boolean isUserExist = LoginController.getInstance().loginProcess(strEmail, strPassword);
            if (!isUserExist){
                new Alert(Alert.AlertType.ERROR,"Email and Password doesn't match").show();
            }else{
                closeWindow();
            }
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
