package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.kiuing.Kiuing;
import com.spronghi.kiu.kiuing.KiuingOperation;
import com.spronghi.kiu.model.PostKiuer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by spronghi on 16/09/16.
 */
public class KiuingJSONParser extends JSONParser<Kiuing>{
    @Override
    public Kiuing parse(String jsonString) {
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Kiuing parse(JSONObject json) {
        Kiuing kiuing = new Kiuing();
        JSONParser<PostKiuer> postJSONParser = new PostKiuerJSONParser();

        try {
            kiuing.setId(json.getInt("id"));
            kiuing.setPost(postJSONParser.parse(json.getJSONObject("post")));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return kiuing;
    }

    @Override
    public JSONObject getJSONObj(Kiuing model) {
        JSONObject obj = new JSONObject();
        JSONParser<PostKiuer> postJSONParser = new PostKiuerJSONParser();
        try {
            obj.put("id", model.getId());
            obj.put("post", postJSONParser.getJSONObj(model.getPost()));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}
