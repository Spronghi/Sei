package service.json;

import integration.control.FilterControl;
import integration.dao.OperationDAO;
import model.KiuingOperation;
import model.Operation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingOperationJSONParser implements JSONParser<KiuingOperation> {
    @Override
    public KiuingOperation parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            OperationDAO operationDAO = new OperationDAO();
            OperationJSONParser operationJSONParser = new OperationJSONParser();

            JSONObject obj = (JSONObject) parser.parse(jsonString);
            KiuingOperation operation = new KiuingOperation();

            List<Operation> list = operationDAO.getAllBy(FilterControl.OPERATION, (String)obj.get("operation"));

            operation.setId((Integer) obj.get("id"));
            operation.setDone((Boolean)obj.get("done"));
            operation.setOperation(list.iterator().next());
            operation.setKiuing((Integer) obj.get("kiuing_id"));

            return operation;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(KiuingOperation operation) {
        OperationJSONParser operationJSONParser = new OperationJSONParser();
        JSONObject obj = new JSONObject();

        obj.put("id", operation.getId());
        obj.put("done", operation.isDone());
        obj.put("kiuing_id", operation.getKiuing());
        obj.put("operation", operation.getOperation().getOperation());
        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<KiuingOperation> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
