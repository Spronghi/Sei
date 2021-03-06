package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.request.ToKiuerRequest;

import java.util.List;

/**
 * Created by spronghi on 20/09/16.
 */
public class ToKiuerRequestService {
    private static String getParameterString(ToKiuerRequest request){
        String url = "id="+Integer.toString(request.getId())+"&";
        url += "seen="+Boolean.toString(request.isSeen())+"&";
        url += "addressee_id="+Integer.toString(request.getAddressee().getId())+"&";
        url += "sender_id="+Integer.toString(request.getSender().getId())+"&";
        url += "post_id="+Integer.toString(request.getPost().getId())+"&";
        url += "type="+request.getType();
        return url;
    }

    public static void create(ToKiuerRequest request){
        String url = "/to_kiuer_request?";
        url += "service=create&";
        url += getParameterString(request);

        JSONParser<ToKiuerRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_KIUER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        request.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(ToKiuerRequest request){
        String url ="/to_kiuer_request?";
        url += "service=delete&";
        url += getParameterString(request);

        JSONParser<ToKiuerRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_KIUER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(ToKiuerRequest request){
        String url ="/to_kiuer_request?";
        url += "service=update&";
        url += getParameterString(request);

        JSONParser<ToKiuerRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_KIUER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }


    public static ToKiuerRequest get(int id){
        String url = "/to_kiuer_request?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<ToKiuerRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_KIUER_REQUEST);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<ToKiuerRequest> getAll(){
        String url = "/to_kiuer_request?";
        url += "service=get_all";
        JSONParser<ToKiuerRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_KIUER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
    public static List<ToKiuerRequest> getAllByAddressee(Kiuer addressee){
        String url = "/to_kiuer_request?";
        url += "service=addressee&";
        url += "addressee_id="+Integer.toString(addressee.getId());
        JSONParser<ToKiuerRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_KIUER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
}
