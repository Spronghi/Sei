package service.json;

import model.Helper;
import model.Helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class HelperJSONParser implements JSONParser<Helper> {
    @Override
    public Helper parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            Helper helper = new Helper();
            helper.setId((Integer) obj.get("id"));
            helper.setUsername((String)obj.get("username"));
            helper.setPassword((String)obj.get("password"));
            helper.setName((String)obj.get("name"));
            helper.setSurname((String)obj.get("surname"));
            helper.setBirth((String)obj.get("birth"));
            helper.setTelephone((String)obj.get("telephone"));
            helper.setFeedback(((Float)obj.get("feedback")));
            helper.setFavoriteCity(((String)obj.get("favorite_city")));
            helper.setProfileStatus((String)obj.get("profile_status"));
            helper.setFavoriteCost(((Double)obj.get("feedback")));
            return helper;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(Helper helper) {
        JSONObject obj = new JSONObject();

        obj.put("id", helper.getId());
        obj.put("username", helper.getUsername());
        obj.put("password", helper.getPassword());
        obj.put("name", helper.getName());
        obj.put("surname", helper.getSurname());
        obj.put("birth", DateFormatter.toString(helper.getBirth(), "dd-MM-yyyy"));
        obj.put("telephone", helper.getTelephone());
        obj.put("feedback", helper.getFeedback());
        obj.put("profile_status", helper.getProfileStatus());
        obj.put("favorite_cost", helper.getFavoriteCost());
        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<Helper> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
