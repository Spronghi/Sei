package service.json;

import model.Kiuing;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingJSONParser implements JSONParser<Kiuing> {
    @Override
    public Kiuing parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            PostKiuerJSONParser postKiuerJSONParser = new PostKiuerJSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            Kiuing kiuing = new Kiuing();
            kiuing.setId(((Integer)obj.get("id")));
            kiuing.setPost(postKiuerJSONParser.parse(((JSONObject)obj.get("post")).toJSONString()));

            return kiuing;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(Kiuing kiuing) {
        PostKiuerJSONParser postKiuerJSONParser = new PostKiuerJSONParser();
        JSONObject obj = new JSONObject();

        obj.put("id", kiuing.getId());
        obj.put("post", postKiuerJSONParser.getJSONObj(kiuing.getPost()).toJSONString());

        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<Kiuing> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
