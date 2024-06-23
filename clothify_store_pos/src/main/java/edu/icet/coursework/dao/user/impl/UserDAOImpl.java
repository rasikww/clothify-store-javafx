package edu.icet.coursework.dao.user.impl;

import edu.icet.coursework.dao.user.UserDAO;
import edu.icet.coursework.dto.User;
import edu.icet.coursework.entity.UserEntity;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();

            UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);

            session.persist(userEntity);
            session.getTransaction().commit();
            isSaved = true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }

        }finally {
            session.close();
        }
        return isSaved;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean isDeleted = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            UserEntity userEntity = session.get(UserEntity.class, id);
            if (userEntity != null) {
                session.remove(userEntity);
                session.getTransaction().commit();
                isDeleted = true;
            }
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return isDeleted;
    }

    @Override
    public UserEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        UserEntity userEntity = null;
        try {
            String sql = "SELECT * FROM user ORDER BY user_id DESC LIMIT 1";
            NativeQuery<UserEntity> query = session.createNativeQuery(sql, UserEntity.class);
            List<UserEntity> results = query.list();

            if (!results.isEmpty()) {
                userEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return userEntity;
    }

    @Override
    public UserEntity getById(Integer id) {
        UserEntity userEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            userEntity = session.get(UserEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return userEntity;
    }

    @Override
    public boolean update(User newUser) {
        boolean isUpdated = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();

            UserEntity userEntity = session.get(UserEntity.class, newUser.getUserId());

            userEntity.setName(newUser.getName());
            userEntity.setEmail(newUser.getEmail());
            if (newUser.getPasswordHash() != null) {
                userEntity.setPasswordHash(newUser.getPasswordHash());
            }
            userEntity.setPhoneNumber(newUser.getPhoneNumber());
            userEntity.setIsAdmin(newUser.getIsAdmin());

            session.flush();
            session.getTransaction().commit();
            isUpdated = true;

        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return isUpdated;
    }

    @Override
    public ObservableList<User> getAll() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        Session session = HibernateUtil.getInstance().getSession();
        try {
            String sql = "SELECT * FROM user WHERE deleted=0";
            NativeQuery<UserEntity> query = session.createNativeQuery(sql, UserEntity.class);
            List<UserEntity> results = query.list();
            for (UserEntity userEntity : results) {
                allUsers.add(new ModelMapper().map(userEntity, User.class));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public User getByEmail(String strEmail) {
        Session session = HibernateUtil.getInstance().getSession();

        String sql = "SELECT * FROM users WHERE email = :strEmail";
        Query<UserEntity> query = session.createNativeQuery(sql, UserEntity.class);
        query.setParameter("strEmail",strEmail);
        UserEntity userEntity = query.getSingleResult();
        if (userEntity != null){
            return new ModelMapper().map(userEntity,User.class);
        }else return new User();

    }
}
