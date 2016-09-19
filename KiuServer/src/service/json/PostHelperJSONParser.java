package service.json;

import model.PostHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class PostHelperJSONParser implements JSONParser<PostHelper>{
    @Override
    public PostHelper parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            HelperJSONParser helperJSONParser = new HelperJSONParser();

            JSONObject obj = (JSONObject) parser.parse(jsonString);
            PostHelper post = new PostHelper();

            post.setId(((Integer)obj.get("id")));
            post.setTitle(((String)obj.get("title")));
            post.setStartDate(((String)obj.get("start")));
            post.setCost((Double)obj.get("cost"));
            post.setOpen((Boolean) obj.get("open"));
            post.setHelper(helperJSONParser.parse(((JSONObject)obj.get("helper")).toJSONString()));
            post.setCity(((String)obj.get("city")));
            post.setEndDate(((String)obj.get("end")));
            return post;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(PostHelper post) {
        HelperJSONParser helperJSONParser = new HelperJSONParser();

        JSONObject obj = new JSONObject();

        obj.put("id", post.getId());
        obj.put("title", post.getTitle());
        obj.put("start", post.getStartDateString());
        obj.put("cost", post.getCost());
        obj.put("open", post.isOpen());
        obj.put("helper", helperJSONParser.getJSONObj(post.getHelper()));
        obj.put("city", post.getCity());
        obj.put("end", post.getEndDateString());

        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<PostHelper> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
