package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;

/**
 * Created by spronghi on 16/09/16.
 */
public class Login {
    public static Kiuer asKiuer(String username, String password){
        String url = "/login?";
        url += "type=kiuer&";
        url += "username="+username+"&";
        url += "password="+password;
        JSONParser<Kiuer> parser = JSONParserFactory.getInstance(JSONParserControl.KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        Log.d("http",jsonString);
        return parser.parse(jsonString);
    }
    public static Helper asHelper(String username, String password){
        String url = "/login?";
        url += "type=helper&";
        url += "username="+username+"&";
        url += "password="+password;
        JSONParser<Helper> parser = JSONParserFactory.getInstance(JSONParserControl.HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        Log.d("http",jsonString);
        return parser.parse(jsonString);
    }
}
