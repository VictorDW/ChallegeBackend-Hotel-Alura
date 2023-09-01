package DAO;

import java.util.List;

public interface JpaPersistence<T>{

    void update(T t);
    T getById(Long id);
    List<T> getAll();
}
