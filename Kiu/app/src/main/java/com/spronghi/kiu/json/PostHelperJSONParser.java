package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.PostHelper;
import com.spronghi.kiu.setup.DateFormatter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatteoSemolaArena on 16/09/2016.
 */
public class PostHelperJSONParser extends JSONParser<PostHelper> {
    @Override
    public PostHelper parse(String jsonString){
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public PostHelper parse(JSONObject obj) {
        PostHelper pHelper = new PostHelper();
        JSONParser<Helper> helperJSONParser = new HelperJSONParser();
        try {
            pHelper.setId(obj.getInt("id"));
            pHelper.setTitle(obj.getString("title"));
            pHelper.setCity(obj.getString("city"));
            pHelper.setStartDate(obj.getString("start_date"));
            pHelper.setEndDate(obj.getString("end_date"));
            pHelper.setCost(obj.getDouble("cost"));
            pHelper.setOpen(obj.getBoolean("open"));
            pHelper.setHelper(helperJSONParser.parse(obj.getJSONObject("helper")));

        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return pHelper;
    }

    @Override
    public JSONObject getJSONObj(PostHelper pHelper){
        JSONObject obj = new JSONObject();
        JSONParser<Helper> helperJSONParser = new HelperJSONParser();
        try {
            obj.put("id", pHelper.getId());
            obj.put("title", pHelper.getTitle());
            obj.put("city", pHelper.getCity());
            obj.put("start_date", DateFormatter.toStringDate(pHelper.getStartDate()));
            obj.put("end_date", DateFormatter.toStringDate(pHelper.getEndDate()));
            obj.put("cost", pHelper.getCostString());
            obj.put("open", pHelper.isOpen());
            obj.put("helper", helperJSONParser.getJSONObj(pHelper.getHelper()));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}
