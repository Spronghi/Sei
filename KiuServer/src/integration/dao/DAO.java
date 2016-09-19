package integration.dao;

import java.util.List;

/**
 * Created by spronghi on 04/07/16.
 */
public interface DAO<T> {
    public T get(int id);
    public List<T> getAll();
    public void create(T entity);
    public void update(T entity);
    public void delete(T entity);
}
