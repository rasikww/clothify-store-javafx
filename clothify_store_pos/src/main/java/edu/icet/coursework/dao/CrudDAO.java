package edu.icet.coursework.dao;

public interface CrudDAO <T> extends SuperDAO {
    boolean save(T entity);
    boolean deleteById(Integer id);
    T getLast();
}
