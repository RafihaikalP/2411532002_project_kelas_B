package dao;

import java.util.List;

public interface CrudDAO<T> {
    void create(T data) throws Exception;
    T findById(String id) throws Exception;
    List<T> findAll() throws Exception;
    void update(T data) throws Exception;
    void delete(String id) throws Exception;
}
