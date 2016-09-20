package com.spronghi.kiu.http;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.Helper;

import java.util.List;

/**
 * Created by spronghi on 16/09/16.
 */
public class HelperService {

    private static String getParameterString(Helper helper){
        String url = "id="+Integer.toString(helper.getId())+"&";
        url += "username="+helper.getUsername()+"&";
        url += "password="+helper.getPassword()+"&";
        url += "email="+helper.getEmail()+"&";
        url += "favorite_city="+helper.getFavoriteCity()+"&";
        url += "favorite_cost="+Double.toString(helper.getFavoriteCost());
        return url;

    }

    public static void create(Helper helper){
        String url = "/helper?";
        url += "service=create&";
        url += getParameterString(helper);

        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        helper.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(Helper helper){
        String url ="/helper?";
        url += "service=delete&";
        url += getParameterString(helper);

        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(Helper helper){
        String url ="/helper?";
        url += "service=update&";
        url += getParameterString(helper);

        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static Helper getByUsername(String username){
        String url = "/helper?service=username&username="+username;
        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static Helper get(int id){
        String url = "/helper?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<Helper> getAll(){
        String url = "/helper?";
        url += "service=get_all";
        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.<Helper>parseArray(jsonString);
    }

}
