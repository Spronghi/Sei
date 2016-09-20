package service.json;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Kiuer;
import model.Place;
import model.PostKiuer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class PostKiuerJSONParser implements JSONParser<PostKiuer> {
    @Override
    public PostKiuer parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            PlaceJSONParser placeJSONParser = new PlaceJSONParser();
            KiuerJSONParser kiuerJSONParser = new KiuerJSONParser();
            HelperJSONParser helperJSONParser = new HelperJSONParser();

            JSONObject obj = (JSONObject) parser.parse(jsonString);
            PostKiuer post = new PostKiuer();

            post.setId(((Integer)obj.get("id")));
            post.setDuration(((Integer)obj.get("duration")));
            post.setCost((Double)obj.get("cost"));
            post.setOpen((Boolean)obj.get("cost"));
            post.setStartDate((String)obj.get("start"));
            post.setToHelperFeedback((Float)obj.get("to_helper_feedback"));
            post.setToKiuerFeedback((Float)obj.get("to_kiuer_feedback"));
            post.setPlace(placeJSONParser.parse(((String)obj.get("place"))));
            post.setKiuer(kiuerJSONParser.parse(((String)obj.get("kiuer"))));
            post.setHelper(helperJSONParser.parse(((String)obj.get("helper"))));
            return post;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(PostKiuer post) {
        PlaceJSONParser placeJSONParser = new PlaceJSONParser();
        KiuerJSONParser kiuerJSONParser = new KiuerJSONParser();
        HelperJSONParser helperJSONParser = new HelperJSONParser();

        JSONObject obj = new JSONObject();

        obj.put("id", post.getId());
        obj.put("start", post.getStartDateString());
        obj.put("duration", post.getDuration());
        obj.put("cost", post.getCost());
        obj.put("open", post.isOpen());
        obj.put("to_helper_feedback", post.getToHelperFeedback());
        obj.put("to_kiuer_feedback", post.getToKiuerFeedback());
        obj.put("place", placeJSONParser.getJSONObj(post.getPlace()));
        obj.put("kiuer", kiuerJSONParser.getJSONObj(post.getKiuer()));
        obj.put("helper", helperJSONParser.getJSONObj(post.getHelper()));

        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<PostKiuer> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
