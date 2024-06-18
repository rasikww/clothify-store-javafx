package edu.icet.coursework.controller.user;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.user.UserBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.user.UserDAO;
import edu.icet.coursework.dto.User;
import edu.icet.coursework.entity.UserEntity;
import edu.icet.coursework.util.BOType;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;

public class UserController {
    private static UserController instance;
    private UserController(){}
    public static UserController getInstance(){
        if (instance == null) {
            return instance = new UserController();
        }
        return instance;
    }
    //--------------------------------------------------------
    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    public String generateNextUserId() {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        UserEntity lastUser = userDAO.getLast();
        if (lastUser == null) {
            return "1";
        }else return String.valueOf((lastUser.getUserId()+1));
    }

    public boolean addUser(User newUser) {
        return userBO.saveUser(newUser);
    }

    public User getUser(String userId) {
        return userBO.getUser(Integer.valueOf(userId));
    }

    public boolean removeUser(String userId) {
        return userBO.deleteUserById(Integer.valueOf(userId));
    }

    public boolean updateUser(User user) {
        return userBO.updateUser(user);
    }

    public ObservableList<User> getAllUsers() {
        return userBO.getAllUsers();
    }
}
