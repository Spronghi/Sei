package service.json;

/**
 * Created by spronghi on 06/07/16.
 */
public class JSONParserFactory {
    @SuppressWarnings("unchecked")
    public static <T> JSONParser<T> getInstance(String key){
        return (JSONParser<T>) JSONParserRegister.getExporter(key);
    }
}
