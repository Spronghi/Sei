import integration.control.FilterControl;
import integration.dao.HelperDAO;
import integration.dao.KiuerDAO;
import integration.dao.OperationDAO;
import integration.dao.PostKiuerDAO;
import model.*;
import service.control.ParserControl;
import service.json.JSONParserFactory;
import service.security.MD5Crypt;

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

        Helper helper = new Helper();
        helper.setId(0);
        helper.setEmail("madassa@gmail.com");
        helper.setUsername("b");
        helper.setPassword(MD5Crypt.crypt("b"));
        helper.setFavoriteCity("Lecce");
        helper.setFavoriteCost(5);
        new HelperDAO().create(helper);

        Operation a = new Operation(0, "accept");
        new OperationDAO().create(a);

        a = new Operation(0, "refuse");
        new OperationDAO().create(a);

        a = new Operation(0, "request");
        new OperationDAO().create(a);


        PostKiuer post = new PostKiuer();
        post.setHelper(new HelperDAO().get(1));
        post.setKiuer(new KiuerDAO().get(1));
        post.setDuration(15);
        post.setCost(10);
        post.setPlace(new Place("Lecce", "ViaRonzoli", "PizzeriaRonzoli"));

        new PostKiuerDAO().create(post);
    }
}
