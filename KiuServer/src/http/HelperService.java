package http;

import integration.control.DAOControl;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import model.Helper;
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
public class HelperService extends HttpServlet {
    private DAO<Helper> dao;
    private JSONParser<Helper> parser;

    private Helper getHelper(HttpServletRequest request){
        Helper helper = new Helper();

        helper.setId(Integer.parseInt(request.getParameter("id")));
        helper.setName(request.getParameter("name"));
        helper.setSurname(request.getParameter("surname"));
        helper.setBirth(request.getParameter("birth"));
        helper.setTelephone(request.getParameter("telephone"));
        helper.setUsername(request.getParameter("username"));
        helper.setPassword(MD5Crypt.crypt(request.getParameter("password")));
        helper.setFeedback(Float.parseFloat(request.getParameter("feedback")));
        helper.setFavoriteCity(request.getParameter("favorite_city"));
        helper.setProfileStatus(request.getParameter("profile_status"));
        helper.setFavoriteCost(Double.parseDouble(request.getParameter("favorite_cost")));
        return helper;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = DAOFactory.getInstance(DAOControl.HELPER);
        parser = JSONParserFactory.getInstance(ParserControl.HELPER);

        String service = request.getParameter("service");
        String responseString = "unavailable service";
        switch(service){
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
        Helper helper = getHelper(request);
        helper.setPassword(MD5Crypt.crypt(helper.getPassword()));
        dao.create(helper);
        return parser.getJSONObj(helper).toJSONString();
    }
    private String update(HttpServletRequest request){
        Helper helper = getHelper(request);
        helper.setPassword(MD5Crypt.crypt(helper.getPassword()));
        dao.update(helper);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        Helper helper = getHelper(request);
        dao.delete(helper);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
}
