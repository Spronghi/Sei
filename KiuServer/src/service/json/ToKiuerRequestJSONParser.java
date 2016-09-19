package service.json;

import model.ToKiuerRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class ToKiuerRequestJSONParser implements JSONParser<ToKiuerRequest>{
    @Override
    public ToKiuerRequest parse(String jsonString) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        try {
            KiuerJSONParser kiuerJSONParser = new KiuerJSONParser();
            HelperJSONParser helperJSONParser = new HelperJSONParser();
            PostKiuerJSONParser postKiuerJSONParser = new PostKiuerJSONParser();
            RequestTypeJSONParser requestTypeJSONParser = new RequestTypeJSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            ToKiuerRequest request = new ToKiuerRequest();
            request.setId(((Integer)obj.get("id")));
            request.setSeen(((Boolean)obj.get("seen")));
            request.setPost(postKiuerJSONParser.parse(((JSONObject)obj.get("post")).toJSONString()));
            request.setAddressee(kiuerJSONParser.parse(((JSONObject)obj.get("addressee")).toJSONString()));
            request.setSender(helperJSONParser.parse(((JSONObject)obj.get("sender")).toJSONString()));
            request.setType(requestTypeJSONParser.parse(((JSONObject)obj.get("type")).toJSONString()));
            return request;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getJSONObj(ToKiuerRequest request) {
        KiuerJSONParser kiuerJSONParser = new KiuerJSONParser();
        HelperJSONParser helperJSONParser = new HelperJSONParser();
        PostKiuerJSONParser postKiuerJSONParser = new PostKiuerJSONParser();
        RequestTypeJSONParser requestTypeJSONParser = new RequestTypeJSONParser();

        JSONObject obj = new JSONObject();

        obj.put("id", request.getId());
        obj.put("seen", request.isSeen());
        obj.put("addressee", kiuerJSONParser.getJSONObj(request.getAddressee()));
        obj.put("sender", helperJSONParser.getJSONObj(request.getSender()));
        obj.put("post", postKiuerJSONParser.getJSONObj(request.getPost()));
        obj.put("type", requestTypeJSONParser.getJSONObj(request.getType()));

        return obj;
    }

    @Override
    public JSONArray getJSONArr(List<ToKiuerRequest> list) {
        JSONArray arr = new JSONArray();
        list.stream().forEach(a->arr.add(getJSONObj(a)));
        return arr;
    }
}
