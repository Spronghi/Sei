package com.spronghi.kiu.json;

/**
 * Created by spronghi on 06/07/16.
 */
public class JSONParserFactory {
    @SuppressWarnings("unchecked")
    public static <T> JSONParser<T> getInstance(String key){
        return (JSONParser<T>) JSONParserRegister.getParser(key);
    }
}
