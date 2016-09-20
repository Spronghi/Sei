package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.PostKiuer;

import java.util.List;

/**
 * Created by spronghi on 16/09/16.
 */
public class PostKiuerService {

    private static String getParameterString(PostKiuer post){
        String url = "id="+Integer.toString(post.getId())+"&";
        url += "kiuer_id="+Integer.toString(post.getKiuer().getId())+"&";
        url += "helper_id="+Integer.toString(post.getHelper().getId())+"&";
        url += "title="+post.getTitle()+"&";
        url += "status="+post.getStatus()+"&";
        url += "start="+post.getStartDateRequest()+"&";
        url += "duration="+Integer.toString((post.getDuration()))+"&";
        url += "cost="+Double.toString(post.getCost())+"&";
        url += "open="+Boolean.toString(post.isOpen())+"&";
        url += "place_id="+Integer.toString(post.getPlace().getId())+"&";
        url += "address="+post.getPlace().getAddress()+"&";
        url += "location="+post.getPlace().getLocation()+"&";
        url += "city="+post.getPlace().getCity();

        return url;
    }

    public static void create(PostKiuer post){
        String url = "/post_kiuer?";
        url += "service=create&";
        url += getParameterString(post);

        JSONParser<PostKiuer> parser = JSONParserFactory.getInstance(JSONParserControl.POST_KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        post.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(PostKiuer post){
        String url ="/post_kiuer?";
        url += "service=delete&";
        url += getParameterString(post);

        JSONParser<PostKiuer> parser = JSONParserFactory.getInstance(JSONParserControl.POST_KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(PostKiuer post){
        String url ="/post_kiuer?";
        url += "service=update&";
        url += getParameterString(post);

        JSONParser<PostKiuer> parser = JSONParserFactory.getInstance(JSONParserControl.POST_KIUER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }


    public static PostKiuer get(int id){
        String url = "/post_kiuer?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<PostKiuer> parser = JSONParserFactory.getInstance(JSONParserControl.POST_KIUER);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<PostKiuer> getAll(){
        String url = "/post_kiuer?";
        url += "service=get_all";
        JSONParser<PostKiuer> parser = JSONParserFactory.getInstance(JSONParserControl.POST_KIUER);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
}
