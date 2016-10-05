import integration.control.FilterControl;
import integration.dao.*;
import model.*;
import org.json.simple.JSONObject;
import service.control.ParserControl;
import service.json.JSONParser;
import service.json.JSONParserFactory;
import service.security.MD5Crypt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by spronghi on 12/08/16.
 */
public class Main {
    public static void main(String [] args){

    }

    private static void populateDB(){
        Kiuer kiuer = new Kiuer();
        kiuer.setId(0);
        kiuer.setEmail("spronghi@gmail.com");
        kiuer.setUsername("a");
        kiuer.setPassword(MD5Crypt.crypt("a"));
        kiuer.setFavoriteCity("Lecce");
        new KiuerDAO().create(kiuer);

        HelperDAO helperDao = new HelperDAO();
        Helper helper = new Helper();
        helper.setFavoriteCost(0.0);
        helper.setPassword(MD5Crypt.crypt("dioporcomerdoso"));
        helper.setUsername("nobody");
        helper.setEmail("nobody@nobody.com");
        helper.setFavoriteCity("nobody");
        helper.setId(3);

        helperDao.create(helper);

        helper = new Helper();
        helper.setId(0);
        helper.setEmail("madassa@gmail.com");
        helper.setUsername("b");
        helper.setPassword(MD5Crypt.crypt("b"));
        helper.setFavoriteCity("Lecce");
        helper.setFavoriteCost(5);
        helperDao.create(helper);

        PostKiuer post = new PostKiuer();
        post.setHelper(new HelperDAO().get(1));
        post.setKiuer(new KiuerDAO().get(1));
        post.setDuration(15);
        post.setCost(10);
        post.setPlace(new Place("Lecce", "ViaRonzoli", "PizzeriaRonzoli"));

        new PostKiuerDAO().create(post);

        RequestType type =  new RequestType(0,"request");
        new RequestTypeDAO().create(type);

        type =  new RequestType(0,"accept");
        new RequestTypeDAO().create(type);

        type =  new RequestType(0,"refuse");
        new RequestTypeDAO().create(type);
    }
}
