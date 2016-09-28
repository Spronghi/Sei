package com.spronghi.kiu.json;

import android.util.Log;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.request.ToHelperRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by spronghi on 20/09/16.
 */
public class ToHelperRequestJSONParser extends JSONParser<ToHelperRequest>{
    @Override
    public ToHelperRequest parse(String jsonString){
        try {
            return parse(new JSONObject(jsonString));
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ToHelperRequest parse(JSONObject obj) {
        JSONParser<Kiuer> kiuerJSONParser = new KiuerJSONParser();
        JSONParser<Helper> helperJSONParser = new HelperJSONParser();
        JSONParser<PostKiuer> postJSONParser = new PostKiuerJSONParser();
        ToHelperRequest request = null;
        try {
            request = new ToHelperRequest(kiuerJSONParser.parse(obj.getJSONObject("sender")),
                    helperJSONParser.parse(obj.getJSONObject("addressee")),
                    postJSONParser.parse(obj.getJSONObject("post")),
                    obj.getJSONObject("type").getString("type"));
            request.setId(obj.getInt("id"));
            request.setSeen(obj.getBoolean("seen"));

        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return request;
    }

    @Override
    public JSONObject getJSONObj(ToHelperRequest request){
        JSONObject obj = new JSONObject();
        JSONParser<Kiuer> kiuerJSONParser = new KiuerJSONParser();
        JSONParser<Helper> helperJSONParser = new HelperJSONParser();
        JSONParser<PostKiuer> postJSONParser = new PostKiuerJSONParser();
        try {
            obj.put("id", request.getId());
            obj.put("addressee", helperJSONParser.getJSONObj(request.getAddressee()));
            obj.put("sender", kiuerJSONParser.getJSONObj(request.getSender()));
            obj.put("post", postJSONParser.getJSONObj(request.getPost()));
            obj.put("seen", request.isSeen());
            obj.put("seen", request.getType());
        } catch (JSONException e) {
            Log.d("JSON", e.getLocalizedMessage());
        }
        return obj;
    }
}
