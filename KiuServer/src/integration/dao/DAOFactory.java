package integration.dao;

/**
 * Created by spronghi on 04/07/16.
 */
public enum DAOFactory {
    INSTANCE;
    @SuppressWarnings("unchecked")
    public static <T> DAO<T> getInstance(String key){
        return (DAO<T>) DAORegister.getDAO(key);
    }
    public static <T> FilterDAO<T> getFilterInstance(String key){
        return (FilterDAO<T>) FilterDAORegister.getFilterDAO(key);
    }
}
