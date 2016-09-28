package com.spronghi.kiu.http;

import android.util.Log;

import com.spronghi.kiu.json.JSONParser;
import com.spronghi.kiu.json.JSONParserControl;
import com.spronghi.kiu.json.JSONParserFactory;
import com.spronghi.kiu.kiuing.Kiuing;
import com.spronghi.kiu.kiuing.KiuingOperation;

import java.util.List;

/**
 * Created by spronghi on 16/09/16.
 */
public class KiuingOperationService {
    private static String getParamaterString(KiuingOperation operation){
        String url = "id="+Integer.toString(operation.getId())+"&";
        url += "done="+Boolean.toString(operation.isDone())+"&";
        url += "kiuing_id="+Integer.toString(operation.getKiuing());
        return url;
    }

    public static void create(KiuingOperation operation){
        String url = "/kiuing_operation?";
        url += "service=create&";
        url += getParamaterString(operation);

        JSONParser<KiuingOperation> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);
        String jsonString = HttpConnector.makeRequest(url);
        operation.setId(parser.parse(jsonString).getId());
    }

    public static boolean delete(KiuingOperation operation){
        String url ="/kiuing_operation?";
        url += "service=delete&";
        url += getParamaterString(operation);

        JSONParser<KiuingOperation> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static boolean update(KiuingOperation operation){
        String url ="/kiuing_operation?";
        url += "service=update&";
        url +=getParamaterString(operation);

        JSONParser<KiuingOperation> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);
        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseResult(jsonString);
    }

    public static KiuingOperation get(int id){
        String url = "/kiuing_operation?";
        url += "service=get&";
        url += "id="+id;
        JSONParser<KiuingOperation> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parse(jsonString);
    }

    public static List<KiuingOperation> getAll(){
        String url = "/kiuing_operation?";
        url += "service=get_all";
        JSONParser<KiuingOperation> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);

        String jsonString = HttpConnector.makeRequest(url);
        return parser.parseArray(jsonString);
    }

    public static List<KiuingOperation> getAllByKiuing(Kiuing kiuing){
        String url = "/kiuing_operation?";
        url += "service=kiuing&";
        url += "kiuing_id="+Integer.toString(kiuing.getId());
        JSONParser<KiuingOperation> parser = JSONParserFactory.getInstance(JSONParserControl.KIUING_OPERATION);

        String jsonString = HttpConnector.makeRequest(url);
        Log.d("kiuing", jsonString);
        return parser.parseArray(jsonString);
    }
}
