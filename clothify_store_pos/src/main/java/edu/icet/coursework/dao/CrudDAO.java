package edu.icet.coursework.dao;

public interface CrudDAO <T,S> extends SuperDAO {
    boolean save(S dto);
    boolean deleteById(Integer id);
    T getLast();
    T getById(Integer id);
    boolean update(S dto);
}
