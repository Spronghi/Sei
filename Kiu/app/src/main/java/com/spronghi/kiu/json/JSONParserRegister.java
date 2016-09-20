package com.spronghi.kiu.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spronghi on 06/07/16.
 */
class JSONParserRegister extends JSONParserControl {
    private static Map<String, JSONParser<?>> container;
    static {
        container = new HashMap<>();
        container.put(KIUER, new KiuerJSONParser());
        container.put(HELPER, new HelperJSONParser());
        container.put(KIUING_OPERATION, new KiuingOperationJSONParser());
        container.put(KIUING, new KiuingJSONParser());
        container.put(POST_KIUER, new PostKiuerJSONParser());
        container.put(PLACE, new PlaceJSONParser());
    }
    static JSONParser<?> getParser(String key){
        return container.get(key);
    }
}
