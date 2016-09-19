package integration.dao;

import integration.control.DAOControl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spronghi on 04/07/16.
 */
class DAORegister extends DAOControl{
    private static Map< String, DAO<?>> container;
    static {
        container = new HashMap<>();
        container.put(HELPER, new HelperDAO());
        container.put(KIUER, new KiuerDAO());
        container.put(KIUING, new KiuingDAO());
        container.put(KIUING_OPERATION, new KiuingOperationDAO());
        container.put(OPERATION, new OperationDAO());
        container.put(PLACE, new PlaceDAO());
        container.put(POST_HELPER, new PostHelperDAO());
        container.put(POST_KIUER, new PostKiuerDAO());
        container.put(REQUEST_TYPE, new RequestTypeDAO());
        container.put(TO_HELPER_REQUEST, new ToHelperRequestDAO());
        container.put(TO_KIUER_REQUEST, new ToKiuerRequestDAO());
    }
    static <T> DAO<T> getDAO(String key){
        return (DAO<T>) container.get(key);
    }
}
