package edu.icet.coursework.controller.login;

import com.jfoenix.controls.JFXButton;
import edu.icet.coursework.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginConfirmEmailFormController {
    public JFXButton btnOk;
    public Button btnClose;

    private void closeWindow(){
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    public void btnOkOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_screen.fxml"));
            Parent root = loader.load();

            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.setScene(new Scene(root));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
