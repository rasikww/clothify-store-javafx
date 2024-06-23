package edu.icet.coursework.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPasswordChangeFormController implements Initializable {

    public JFXButton btnCancel;
    public Button btnClose;
    public JFXButton btnConfirm;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            txtEmail.requestFocus();
        });
    }

    private void closeWindow(){
        Stage stage = (Stage) btnConfirm.getScene().getWindow();
        stage.close();
    }
    public void btnCancelOnAction(ActionEvent actionEvent) {
        closeWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_screen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
        emailPasswordAddProcess();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        emailPasswordAddProcess();
    }
    private void emailPasswordAddProcess() {
        String strEmail = txtEmail.getText();
        String strPassword = txtPassword.getText();

        if(!(EmailValidator.getInstance().isValid(strEmail))){//add password validator here at the end of implementation
            new Alert(Alert.AlertType.ERROR,"Enter Valid Email & Password").show();
        } else {
            boolean isAdded = LoginController.getInstance().addEmailPassword(strEmail, strPassword);
            if (!isAdded){
                new Alert(Alert.AlertType.ERROR,"Email doesn't match for any account").show();
            }else{
                closeWindow();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_screen.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
