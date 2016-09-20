package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.Place;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.setup.DateFormatter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatteoSemolaArena on 16/09/2016.
 */
public class PostKiuerJSONParser extends JSONParser<PostKiuer> {
    @Override
    public PostKiuer parse(String jsonString){
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public PostKiuer parse(JSONObject obj) {
        PostKiuer pKiuer = new PostKiuer();
        JSONParser<Kiuer> kiuerJSONParser = new KiuerJSONParser();
        JSONParser<Helper> helperJSONParser = new HelperJSONParser();
        JSONParser<Place> placeJSONParser = new PlaceJSONParser();
        try {
            pKiuer.setId(obj.getInt("id"));
            pKiuer.setStartDate(obj.getString("start"));
            pKiuer.setDuration(obj.getInt("duration"));
            pKiuer.setCost(obj.getDouble("cost"));
            pKiuer.setOpen(obj.getBoolean("open"));
            pKiuer.setToHelperFeedback(obj.getLong("to_helper_feedback"));
            pKiuer.setToKiuerFeedback(obj.getLong("to_kiuer_feedback"));
            pKiuer.setKiuer(kiuerJSONParser.parse(obj.getJSONObject("kiuer")));
            pKiuer.setHelper(helperJSONParser.parse(obj.getJSONObject("helper")));
            pKiuer.setPlace(placeJSONParser.parse(obj.getJSONObject("place")));

        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return pKiuer;
    }

    @Override
    public JSONObject getJSONObj(PostKiuer pKiuer){
        JSONObject obj = new JSONObject();
        JSONParser<Kiuer> kiuerJSONParser = new KiuerJSONParser();
        JSONParser<Helper> helperJSONParser = new HelperJSONParser();
        JSONParser<Place> placeJSONParser = new PlaceJSONParser();
        try {
            obj.put("id", pKiuer.getId());
            obj.put("start_date", DateFormatter.toStringDate(pKiuer.getStartDate()));
            obj.put("to_helper_feedback", pKiuer.getToHelperFeedback());
            obj.put("to_kiuer_feedback", pKiuer.getToKiuerFeedback());
            obj.put("duration", pKiuer.getDuration());
            obj.put("cost", pKiuer.getCostString());
            obj.put("open", pKiuer.isOpen());
            obj.put("kiuer", kiuerJSONParser.getJSONObj(pKiuer.getKiuer()));
            obj.put("helper", helperJSONParser.getJSONObj(pKiuer.getHelper()));
            obj.put("place", placeJSONParser.getJSONObj(pKiuer.getPlace()));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}

