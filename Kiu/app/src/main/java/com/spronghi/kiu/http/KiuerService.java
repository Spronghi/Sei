package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.DateFormatter;

import java.util.List;

/**
 * Created by Angelo on 16/09/2016.
 */
public class KiuerService {

    private static String getParameterString(Kiuer kiuer){
        String url = "id="+Integer.toString(kiuer.getId())+"&";
        url += "username="+kiuer.getUsername()+"&";
		url += "password="+kiuer.getPassword()+"&";
		url += "email="+kiuer.getEmail()+"&";
        url += "favorite_city="+kiuer.getFavoriteCity();
        return url;

    }

    public static void create(Kiuer kiuer){
        String url = "/kiuer?";
        url += "service=create&";
        url += getParameterString(kiuer);

        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        Log.d("kiuer", jsonString);
        kiuer.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(Kiuer kiuer){
        String url ="/kiuer?";
        url += "service=delete&";
        url += getParameterString(kiuer);

        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(Kiuer kiuer){
        String url ="/kiuer?";
        url += "service=update&";
        url += getParameterString(kiuer);

        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static Kiuer getByUsername(String username){
        String url = "/kiuer?service=username&username="+username;
        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static Kiuer get(int id){
        String url = "/kiuer?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<Kiuer> getAll(){
        String url = "/kiuer?";
        url += "service=get_all&";
        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.<Kiuer>parseArray(jsonString);
    }
    public static double getFeedback(Kiuer kiuer){
        String url ="/kiuer?";
        url += "service=feedback&";
        url += getParameterString(kiuer);
        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseFeedback(jsonString);
    }
}
