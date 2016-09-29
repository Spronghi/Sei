package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.kiuing.Kiuing;
import com.spronghi.kiu.model.PostKiuer;

import java.util.List;

/**
 * Created by spronghi on 16/09/16.
 */
public class KiuingService {
    private static String getParamaterString(Kiuing kiuing){
        String url = "id="+Integer.toString(kiuing.getId())+"&";
        url += "kiuer_post_id="+Integer.toString(kiuing.getPost().getId());
        return url;
    }

    public static void create(Kiuing kiuing){
        String url = "/kiuing?";
        url += "service=create&";
        url += getParamaterString(kiuing);

        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING);
        String jsonString = HttpConnector.makeRequest(url);
        kiuing.setId(parser.parse(jsonString).getId());

        kiuing.setOperationList(KiuingOperationService.getAllByKiuing(kiuing));
    }

    public static boolean delete(Kiuing kiuing){
        String url ="/kiuing?";
        url += "service=delete&";
        url += getParamaterString(kiuing);

        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(Kiuing kiuing){
        String url ="/kiuing?";
        url += "service=update&";
        url +=getParamaterString(kiuing);

        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static Kiuing get(int id){
        String url = "/kiuing?";
        url += "service=get&";
        url += "id="+Integer.toString(id);
        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING);

        String jsonString = HttpConnector.makeRequest(url);
        Kiuing kiuing = parser.parse(jsonString);
        kiuing.setOperationList(KiuingOperationService.getAllByKiuing(kiuing));

        return kiuing;
    }

    public static List<Kiuing> getAll(){
        String url = "/kiuing?";
        url += "service=get_all";
        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING);

        String jsonString = HttpConnector.makeRequest(url);
        List<Kiuing> list = parser.parseArray(jsonString);
        for(Kiuing kiuing : list)
            kiuing.setOperationList(KiuingOperationService.getAllByKiuing(kiuing));

        return list;
    }
    public static List<Kiuing> getAllByPostKiuer(PostKiuer post){
        String url = "/kiuing?";
        url += "service=post_kiuer&";
        url += "post_kiuer_id="+post.getId();
        JSONParser<Kiuing> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING);

        String jsonString = HttpConnector.makeRequest(url);
        List<Kiuing> list = parser.parseArray(jsonString);
        for(Kiuing kiuing : list)
            kiuing.setOperationList(KiuingOperationService.getAllByKiuing(kiuing));

        return list;
    }
}
