package integration.dao;

import integration.control.DAOControl;
import integration.control.FilterControl;
import model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spronghi on 05/07/16.
 */
class FilterDAORegister extends DAOControl{
    private static Map<String, FilterDAO<?>> container;
    static {
        container = new HashMap<>();
        container.put(HELPER, new HelperDAO());
        container.put(KIUER, new KiuerDAO());
        container.put(POST_KIUER, new PostKiuerDAO());
        container.put(POST_HELPER, new PostHelperDAO());
        container.put(POST_HELPER, new PostHelperDAO());
        container.put(KIUING_OPERATION, new KiuingOperationDAO());
        container.put(KIUING_OPERATION, new KiuingOperationDAO());
        container.put(TO_HELPER_REQUEST, new ToHelperRequestDAO());
        container.put(TO_KIUER_REQUEST, new ToKiuerRequestDAO());
    }
    static <T> FilterDAO<T> getFilterDAO(String key){
        return (FilterDAO<T>) container.get(key);
    }
}
