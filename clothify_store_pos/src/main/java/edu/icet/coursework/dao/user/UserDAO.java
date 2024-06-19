package edu.icet.coursework.dao.user;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.User;
import edu.icet.coursework.entity.UserEntity;
import javafx.collections.ObservableList;

public interface UserDAO extends CrudDAO<UserEntity,User> {
    ObservableList<User> getAll();
}
