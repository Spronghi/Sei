import integration.control.DAOControl;
import integration.control.FilterControl;
import integration.dao.*;
import model.*;
import service.control.ParserControl;
import service.json.JSONParserFactory;
import service.json.PostHelperJSONParser;
import service.json.PostKiuerJSONParser;
import service.security.MD5Crypt;
import util.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 12/08/16.
 */
public class Main {
    public static void main(String [] args){
        List<Kiuer> list = DAOFactory.<Kiuer>getFilterInstance(DAOControl.KIUER).getAllBy(FilterControl.USERNAME, "a");
        Kiuer kiuer = list.iterator().next();
        System.out.println(kiuer.toString());
        if(kiuer!=null && kiuer.getPassword().equals(MD5Crypt.crypt("a")))
            System.out.println(JSONParserFactory.<Kiuer>getInstance(ParserControl.KIUER).getJSONObj(kiuer).toJSONString());

    }
}
