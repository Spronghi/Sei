package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.model.Place;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatteoSemolaArena on 16/09/2016.
 */
public class PlaceJSONParser extends JSONParser<Place> {
    @Override
    public Place parse(String jsonString){
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Place parse(JSONObject obj) {
        try {
            Place place = new Place();
            place.setId(obj.getInt("id"));
            place.setCity(obj.getString("city"));
            place.setAddress(obj.getString("address"));
            place.setLocation(obj.getString("location"));
            return place;
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;    }

    @Override
    public JSONObject getJSONObj(Place place){
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", place.getId());
            obj.put("city", place.getCity());
            obj.put("address", place.getAddress());
            obj.put("location", place.getLocation());
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}
