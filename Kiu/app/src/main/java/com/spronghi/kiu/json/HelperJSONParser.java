package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.model.Helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatteoSemolaArena on 16/09/2016.
 */
public class HelperJSONParser extends JSONParser<Helper> {
    @Override
    public Helper parse(String jsonString){
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Helper parse(JSONObject obj) {
        Helper helper = new Helper();
        try {
            helper.setId(obj.getInt("id"));
            helper.setUsername(obj.getString("username"));
            helper.setEmail(obj.getString("email"));
            helper.setFavoriteCity(obj.getString("favorite_city"));
            helper.setPassword(obj.getString("password"));
            helper.setFavoriteCost(obj.getDouble("favorite_cost"));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return helper;
    }

    @Override
    public JSONObject getJSONObj(Helper helper){
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", helper.getId());
            obj.put("username", helper.getUsername());
            obj.put("email", helper.getEmail());
            obj.put("favorite_city", helper.getFavoriteCity());
            obj.put("password", helper.getPassword());
            obj.put("favorite_cost", helper.getFavoriteCost());
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}
