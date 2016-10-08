package service.json;

import model.RequestType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class RequestTypeJSONParser implements JSONParser<RequestType> {
    @Override
    public RequestType parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            RequestType request = new RequestType((Integer) obj.get("id"), (String)obj.get("type"));
            return request;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(RequestType request) {
        JSONObject obj = new JSONObject();

        obj.put("id", request.getId());
        obj.put("type", request.getType());

        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<RequestType> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
