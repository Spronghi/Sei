package service.json;

import model.Operation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class OperationJSONParser implements JSONParser<Operation> {
    @Override
    public Operation parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            return new Operation((Integer)obj.get("id"), (String)obj.get("operation"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(Operation operation) {
        JSONObject obj = new JSONObject();

        obj.put("id", operation.getId());
        obj.put("operation", operation.getOperation());
        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<Operation> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
