package com.spronghi.kiu.http;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.request.ToHelperRequest;

import java.util.List;

/**
 * Created by spronghi on 20/09/16.
 */
public class ToHelperRequestService {
    private static String getParameterString(ToHelperRequest request){
        String url = "id="+Integer.toString(request.getId())+"&";
        url += "seen="+Boolean.toString(request.isSeen())+"&";
        url += "addressee_id="+Integer.toString(request.getAddressee().getId())+"&";
        url += "sender_id="+Integer.toString(request.getSender().getId())+"&";
        url += "post_id="+Integer.toString(request.getPost().getId())+"&";
        url += "type="+request.getType();
        return url;
    }

    public static void create(ToHelperRequest request){
        String url = "/to_helper_request?";
        url += "service=create&";
        url += getParameterString(request);

        JSONParser<ToHelperRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_HELPER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        request.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(ToHelperRequest request){
        String url ="/to_helper_request?";
        url += "service=delete&";
        url += getParameterString(request);

        JSONParser<ToHelperRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_HELPER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(ToHelperRequest request){
        String url ="/to_helper_request?";
        url += "service=update&";
        url += getParameterString(request);

        JSONParser<ToHelperRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_HELPER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }


    public static ToHelperRequest get(int id){
        String url = "/to_helper_request?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<ToHelperRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_HELPER_REQUEST);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<ToHelperRequest> getAll(){
        String url = "/to_helper_request?";
        url += "service=get_all";
        JSONParser<ToHelperRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_HELPER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
    public static List<ToHelperRequest> getAllByAddressee(Helper addressee){
        String url = "/to_helper_request?";
        url += "service=addressee&";
        url += "addressee_id="+Integer.toString(addressee.getId());
        JSONParser<ToHelperRequest> parser = JSONParserFactory.getInstance(JSONParserControl.TO_HELPER_REQUEST);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }
}
