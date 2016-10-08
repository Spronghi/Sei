package http;

import integration.control.FilterControl;
import integration.dao.HelperDAO;
import integration.dao.PostKiuerDAO;
import model.Helper;
import model.PostKiuer;
import org.json.simple.JSONObject;
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
import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class HelperService extends HttpServlet {
    private HelperDAO dao;
    private JSONParser<Helper> parser;

    private Helper getHelper(HttpServletRequest request){
        Helper helper = new Helper();

        helper.setId(Integer.parseInt(request.getParameter("id")));
        helper.setEmail(request.getParameter("email"));
        helper.setUsername(request.getParameter("username"));
        helper.setPassword(MD5Crypt.crypt(request.getParameter("password")));
        helper.setFavoriteCity(request.getParameter("favorite_city"));
        helper.setFavoriteCost(Double.parseDouble(request.getParameter("favorite_cost")));
        return helper;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = new HelperDAO();
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
            case HttpControl.CITY:
                responseString = getAllByCity(request);
                break;
            case HttpControl.FEEDBACK:
                responseString = getFeedback(request);
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
        dao.create(helper);
        return parser.getJSONObj(helper).toJSONString();
    }
    private String update(HttpServletRequest request){
        Helper helper = getHelper(request);
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
    private String getAllByCity(HttpServletRequest request){
        return parser.getJSONArr(dao.getAllBy(HttpControl.CITY, request.getParameter("city"))).toJSONString();
    }
    private String getFeedback(HttpServletRequest request){
        PostKiuerDAO postDAO = new PostKiuerDAO();
        JSONObject jsonObject = new JSONObject();
        List<PostKiuer> postList = postDAO.getAllBy(FilterControl.HELPER, Integer.toString(getHelper(request).getId()));
        int i = 0;
        float feedback = 0;
        for(PostKiuer post : postList) {
            if(post.getToHelperFeedback() != 0){
                feedback += post.getToHelperFeedback();
                i++;
            }
        }
        if(feedback != 0){
            feedback = feedback / i;
        }
        jsonObject.put("feedback", Float.toString(feedback));
        return jsonObject.toJSONString();
    }
}
