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
            kiuer.setName(obj.getString("name"));
            kiuer.setSurname(obj.getString("surname"));
            kiuer.setBirth(obj.getString("birth"));
            kiuer.setTelephone(obj.getString("telephone"));
            kiuer.setFeedback((float) obj.getDouble("feedback"));
            kiuer.setFavoriteCity(obj.getString("favorite_city"));
            kiuer.setProfileStatus(obj.getString("profile_status"));
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
            obj.put("name", kiuer.getName());
            obj.put("surname", kiuer.getSurname());
            obj.put("birth", kiuer.getBirth());
            obj.put("telephone", kiuer.getTelephone());
            obj.put("feedback", kiuer.getFeedback());
            obj.put("favorite_city", kiuer.getFavoriteCity());
            obj.put("profile_status", kiuer.getProfileStatus());
            obj.put("password", kiuer.getPassword());
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}

