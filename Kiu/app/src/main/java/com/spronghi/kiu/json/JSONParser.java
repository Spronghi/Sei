package com.spronghi.kiu.json;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 05/07/16.
 */
public abstract class JSONParser<T> {
    abstract public T parse(String jsonString);
    abstract public T parse(JSONObject json);
    abstract public JSONObject getJSONObj(T model);
    public boolean parseResult(String jsonString){
        try {
            JSONObject obj = new JSONObject(jsonString);
            return obj.getBoolean("result");
        } catch (JSONException e) {
            return false;
        }
    }
    public List<T> parseArray(String arrayString){
        try {
            List<T> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(arrayString);
            for(int i=0; i<jsonArray.length(); i++) {
                list.add(parse(jsonArray.getJSONObject(i)));
            }
            return list;
        } catch (JSONException e) {
            return new ArrayList<>();
        }
    }
    public double parseFeedback(String jsonString){
        try {
            JSONObject obj = new JSONObject(jsonString);
            return obj.getDouble("feedback");
        } catch (JSONException e) {
            return 0.0;
        }
    }
}
