package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.kiuing.KiuingOperation;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by spronghi on 16/09/16.
 */
public class KiuingOperationJSONParser extends JSONParser<KiuingOperation>{
    @Override
    public KiuingOperation parse(String jsonString) {
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public KiuingOperation parse(JSONObject json) {
        KiuingOperation operation = new KiuingOperation();
        try {
            operation.setId(json.getInt("id"));
            operation.setDone(json.getBoolean("done"));
            operation.setKiuing(json.getInt("kiuing_id"));
            operation.setOperation(json.getString("operation"));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return operation;
    }

    @Override
    public JSONObject getJSONObj(KiuingOperation model) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", model.getId());
            obj.put("operation", model.getOperation());
            obj.put("kiuing_id", model.getKiuing());
            obj.put("done", model.isDone());

        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}
