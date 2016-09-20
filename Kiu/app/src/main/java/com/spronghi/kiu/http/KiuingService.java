package com.spronghi.kiu.http;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.kiuing.Kiuing;

import java.util.List;

/**
 * Created by spronghi on 16/09/16.
 */
public class KiuingService {
    private static String getParamaterString(Kiuing kiuing){
        String url = "id="+Integer.toString(kiuing.getId())+"&";
        url += "post_id="+Integer.toString(kiuing.getPost().getId());
        return url;
    }

    public static void create(Kiuing kiuing){
        String url = "/kiuing?";
        url += "service=create&";
        url += getParamaterString(kiuing);

        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);
        String jsonString = HttpConnector.makeRequest(url);
        kiuing.setId(parser.parse(jsonString).getId());

    }

    public static boolean delete(Kiuing kiuing){
        String url ="/kiuing?";
        url += "service=delete&";
        url += getParamaterString(kiuing);

        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(Kiuing kiuing){
        String url ="/kiuing?";
        url += "service=update&";
        url +=getParamaterString(kiuing);

        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static Kiuing get(int id){
        String url = "/kiuing?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<Kiuing> getAll(){
        String url = "/kiuing?";
        url += "service=get_all";
        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
}
