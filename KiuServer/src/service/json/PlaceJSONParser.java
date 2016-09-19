package service.json;

import model.Place;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.json.JSONParser;
import util.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class PlaceJSONParser implements JSONParser<Place> {
    @Override
    public Place parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            Place place = new Place();
            place.setId(((Integer)obj.get("id")));
            place.setCity(((String)obj.get("title")));
            place.setAddress(((String)obj.get("address")));
            place.setLocation(((String)obj.get("location")));
            return place;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(Place place) {
        JSONObject obj = new JSONObject();

        obj.put("id", place.getId());
        obj.put("city", place.getCity());
        obj.put("address", place.getAddress());
        obj.put("location", place.getLocation());
        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<Place> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
