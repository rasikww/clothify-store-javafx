package edu.icet.coursework.bo.user;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.User;
import javafx.collections.ObservableList;

public interface UserBO extends SuperBO {
    boolean saveUser(User user);
    boolean deleteUserById(Integer id);
    User getUser(Integer userId);
    boolean updateUser(User user);
    ObservableList<User> getAllUsers();
}
