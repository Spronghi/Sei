package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.PostHelper;

import java.util.List;

/**
 * Created by spronghi on 16/09/16.
 */
public class PostHelperService {
    private static String getParameterString(PostHelper post){
        String url = "id="+Integer.toString(post.getId())+"&";
        url += "helper_id="+Integer.toString(post.getHelper().getId())+"&";
        url += "title="+post.getTitle()+"&";
        url += "city="+post.getCity()+"&";
        url += "start="+post.getStartDateRequest()+"&";
        url += "end="+post.getEndDateRequest()+"&";
        url += "cost="+Double.toString(post.getCost())+"&";
        url += "open="+Boolean.toString(post.isOpen());
        return url;
    }

    public static void create(PostHelper post){
        String url = "/post_helper?";
        url += "service=create&";
        url += getParameterString(post);

        JSONParser<PostHelper> parser = JSONParserFactory.getInstance(JSONParserControl.POST_HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        post.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(PostHelper post){
        String url ="/post_helper?";
        url += "service=delete&";
        url += getParameterString(post);

        JSONParser<PostHelper> parser = JSONParserFactory.getInstance(JSONParserControl.POST_HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(PostHelper post){
        String url ="/post_helper?";
        url += "service=update&";
        url += getParameterString(post);

        JSONParser<PostHelper> parser = JSONParserFactory.getInstance(JSONParserControl.POST_HELPER);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }


    public static PostHelper get(int id){
        String url = "/post_helper?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<PostHelper> parser = JSONParserFactory.getInstance(JSONParserControl.POST_HELPER);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<PostHelper> getAll(){
        String url = "/post_helper?";
        url += "service=get_all";
        JSONParser<PostHelper> parser = JSONParserFactory.getInstance(JSONParserControl.POST_HELPER);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
}
