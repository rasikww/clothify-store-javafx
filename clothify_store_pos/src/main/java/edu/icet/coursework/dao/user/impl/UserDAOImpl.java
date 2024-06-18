package edu.icet.coursework.dao.user.impl;

import edu.icet.coursework.dao.user.UserDAO;
import edu.icet.coursework.dto.User;
import edu.icet.coursework.entity.UserEntity;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(UserEntity entity) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            isSaved = true;
            System.out.println("in try: "+ entity);
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println("in catch");

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
    public boolean update(UserEntity newUserEntity) {
        boolean isUpdated = false;
//        Session session = HibernateUtil.getInstance().getSession();
//        try {
//            session.getTransaction().begin();
//            UserEntity userEntity = session.get(UserEntity.class, newUserEntity.getUserId());
//            userEntity.setName(newUserEntity.getName());
//            userEntity.setCompany(newUserEntity.getCompany());
//            userEntity.setEmail(newUserEntity.getEmail());
//            userEntity.setPhoneNumber(newUserEntity.getPhoneNumber());
//            session.flush();
//            session.getTransaction().commit();
//            isUpdated = true;
//        } catch (Exception e) {
//            if (session.getTransaction().isActive()){
//                session.getTransaction().rollback();
//            }
//        } finally {
//            session.close();
//        }
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
}