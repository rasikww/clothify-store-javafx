package edu.icet.coursework.bo.user.impl;

import edu.icet.coursework.bo.user.UserBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.user.UserDAO;
import edu.icet.coursework.dto.User;
import edu.icet.coursework.entity.UserEntity;
import edu.icet.coursework.util.DAOType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
    @Override
    public boolean saveUser(User userDTO) {
        return userDAO.save(new ModelMapper().map(userDTO, UserEntity.class));
    }

    @Override
    public boolean deleteUserById(Integer userId) {
        return userDAO.deleteById(userId);
    }

    @Override
    public User getUser(Integer userId) {
        try {
            return new ModelMapper().map(userDAO.getById(userId),User.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.update(new ModelMapper().map(user, UserEntity.class));
    }

    @Override
    public ObservableList<User> getAllUsers() {
        return userDAO.getAll();
    }
}
