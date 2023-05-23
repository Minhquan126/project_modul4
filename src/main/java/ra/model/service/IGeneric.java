package ra.model.service;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean deleteById(int id);
    T findById(int id);
}
