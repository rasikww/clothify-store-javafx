package edu.icet.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Getter
    @Setter
    private static Stage primaryStage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_screen.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}