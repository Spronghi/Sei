package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.model.Kiuer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatteoSemolaArena on 16/09/2016.
 */
public class KiuerJSONParser extends JSONParser<Kiuer> {
    @Override
    public Kiuer parse(String jsonString){
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Kiuer parse(JSONObject obj) {
        Kiuer kiuer = new Kiuer();
        try {
            kiuer.setId(obj.getInt("id"));
            kiuer.setUsername(obj.getString("username"));
            kiuer.setEmail(obj.getString("email"));
            kiuer.setFavoriteCity(obj.getString("favorite_city"));
            kiuer.setPassword(obj.getString("password"));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return kiuer;
    }

    @Override
    public JSONObject getJSONObj(Kiuer kiuer){
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", kiuer.getId());
            obj.put("username", kiuer.getUsername());
            obj.put("email", kiuer.getEmail());
            obj.put("favorite_city", kiuer.getFavoriteCity());
            obj.put("password", kiuer.getPassword());
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}

