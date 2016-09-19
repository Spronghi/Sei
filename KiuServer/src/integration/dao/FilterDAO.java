package integration.dao;

import java.util.List;

/**
 * Created by spronghi on 05/07/16.
 */
public interface FilterDAO<T> extends DAO<T>{
    public List<T> getAllBy(String flag, Object key);
}
