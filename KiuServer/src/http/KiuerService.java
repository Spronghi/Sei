package http;

import integration.control.FilterControl;
import integration.dao.KiuerDAO;
import integration.dao.PostKiuerDAO;
import model.Helper;
import model.Kiuer;
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
        Kiuer kiuer = getKiuer(request);
        dao.create(kiuer);
        return parser.getJSONObj(kiuer).toJSONString();
    }
    private String update(HttpServletRequest request){
        Kiuer kiuer = getKiuer(request);
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

    private String getFeedback(HttpServletRequest request){
        PostKiuerDAO postDAO = new PostKiuerDAO();
        JSONObject jsonObject = new JSONObject();
        List<PostKiuer> postList = postDAO.getAllBy(FilterControl.KIUER, Integer.toString(getKiuer(request).getId()));
        int i = 0;
        float feedback = 0;
        for(PostKiuer post : postList) {
            if(post.getToKiuerFeedback() != 0){
                feedback += post.getToKiuerFeedback();
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
