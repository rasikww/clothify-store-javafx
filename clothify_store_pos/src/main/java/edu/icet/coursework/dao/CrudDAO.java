package edu.icet.coursework.dao;

public interface CrudDAO <T> extends SuperDAO {
    boolean save(T entity);
    boolean delete(String id);
    T get(T entity);
}
