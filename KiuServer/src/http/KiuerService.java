package http;

import integration.dao.KiuerDAO;
import model.Kiuer;
import service.control.ParserControl;
import service.json.JSONParser;
import service.json.JSONParserFactory;
import service.security.MD5Crypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuerService extends HttpServlet {
    private KiuerDAO dao;
    private JSONParser<Kiuer> parser;

    private Kiuer getKiuer(HttpServletRequest request){
        Kiuer kiuer = new Kiuer();

        kiuer.setId(Integer.parseInt(request.getParameter("id")));
        kiuer.setEmail(request.getParameter("email"));
        kiuer.setUsername(request.getParameter("username"));
        kiuer.setPassword(MD5Crypt.crypt(request.getParameter("password")));
        kiuer.setFavoriteCity(request.getParameter("favorite_city"));
        return kiuer;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = new KiuerDAO();
        parser = JSONParserFactory.getInstance(ParserControl.KIUER);

        String service = request.getParameter("service");
        String responseString = "unavailable service";
        switch(service){
            case HttpControl.TRY:
                responseString = "SUCCESSFULLY CONNECTED!!";
                break;
            case HttpControl.CREATE:
                responseString = create(request);
                break;
            case HttpControl.UPDATE:
                responseString = update(request);
                break;
            case HttpControl.DELETE:
                responseString = delete(request);
                break;
            case HttpControl.GET:
                responseString = get(request);
                break;
            case HttpControl.GET_ALL:
                responseString = getAll();
                break;
        }
        out.println(responseString);
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    private String create(HttpServletRequest request){
        Kiuer kiuer = getKiuer(request);
        kiuer.setPassword(MD5Crypt.crypt(kiuer.getPassword()));
        dao.create(kiuer);
        return parser.getJSONObj(kiuer).toJSONString();
    }
    private String update(HttpServletRequest request){
        Kiuer kiuer = getKiuer(request);
        kiuer.setPassword(MD5Crypt.crypt(kiuer.getPassword()));
        dao.update(kiuer);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        Kiuer kiuer = getKiuer(request);
        dao.delete(kiuer);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
}
