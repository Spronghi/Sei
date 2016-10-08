package service.json;

import model.Kiuer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuerJSONParser implements JSONParser<Kiuer>{
    @Override
    public Kiuer parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            Kiuer kiuer = new Kiuer();
            kiuer.setId(((Integer)obj.get("id")));
            kiuer.setUsername((String)obj.get("username"));
            kiuer.setPassword((String)obj.get("password"));
            kiuer.setEmail(((String)obj.get("email")));
            kiuer.setFavoriteCity(((String)obj.get("favorite_city")));
            return kiuer;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(Kiuer kiuer) {
        JSONObject obj = new JSONObject();

        obj.put("id", kiuer.getId());
        obj.put("username", kiuer.getUsername());
        obj.put("password", kiuer.getPassword());
        obj.put("email", kiuer.getEmail());
        obj.put("favorite_city", kiuer.getFavoriteCity());
        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<Kiuer> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
