package service.json;

import model.Place;
import service.control.ParserControl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spronghi on 06/07/16.
 */
class JSONParserRegister extends ParserControl {
    private static Map<String, JSONParser<?>> container;
    static {
        container = new HashMap<>();
        container.put(HELPER, new HelperJSONParser());
        container.put(KIUER, new KiuerJSONParser());
        container.put(KIUING, new KiuingJSONParser());
        container.put(KIUING_OPERATION, new KiuingOperationJSONParser());
        container.put(OPERATION, new OperationJSONParser());
        container.put(PLACE, new PlaceJSONParser());
        container.put(POST_HELPER, new PostHelperJSONParser());
        container.put(POST_KIUER, new PostKiuerJSONParser());
        container.put(REQUEST_TYPE, new RequestTypeJSONParser());
        container.put(TO_HELPER_REQUEST, new ToHelperRequestJSONParser());
        container.put(TO_KIUER_REQUEST, new ToKiuerRequestJSONParser());
    }
    static JSONParser<?> getExporter(String key){
        return container.get(key);
    }
}
