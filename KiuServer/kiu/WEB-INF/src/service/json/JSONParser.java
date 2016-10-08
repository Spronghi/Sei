package service.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by spronghi on 05/07/16.
 */
public interface JSONParser<T> {
    public T parse(String jsonString);
    public JSONObject getJSONObj(T model);
    public JSONArray getJSONArr(List<T> list);
    public static JSONObject getSuccessJSON(boolean success){
        JSONObject obj = new JSONObject();
        obj.put("result", success);
        return obj;
    }
}