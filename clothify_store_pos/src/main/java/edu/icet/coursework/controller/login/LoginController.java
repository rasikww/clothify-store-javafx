package edu.icet.coursework.controller.login;

import edu.icet.coursework.dto.User;
import edu.icet.coursework.util.CrudUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private static LoginController instance;

    private LoginController(){}
    public static LoginController getInstance(){
        if (instance == null) {
            return instance = new LoginController();
        }
        return instance;
    }
    //-----------------------------

    private boolean areEmailAndPwdHashMatched(String strEmail, String hashPassword){

        return false;//remove this
    }
    private String strToHashPassword(String strPassword){
        String hashPassword=null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strPassword.getBytes());

            byte[] bytes = md5.digest();

            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }

    public boolean loginProcess(String strEmail, String strPassword) {
        //convert string password to md5 hash
        String hashedPassword = strToHashPassword(strPassword);
        //user password check performing here
        User obtainedUser = getUser(strEmail,hashedPassword);
        //if false go back
        if (obtainedUser == null) {
            return false;
        }else{
            if (obtainedUser.getIsAdmin()){
                openAdminWindow();
            }else openEmployeeWindow();
            return true;
        }
        //if true change ui according to is_admin property
    }

    private void openEmployeeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_interface.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clothify Store Employee View");

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openAdminWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin_interface.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clothify Store Admin View");

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUser(String strEmail, String hashedPassword){
        String sql = "SELECT * FROM user where email=? AND password_hash=?";
        User obtainedUser=null;
        try {
            ResultSet resultSet = CrudUtil.execute(sql, strEmail, hashedPassword);
            while (resultSet.next()){
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
