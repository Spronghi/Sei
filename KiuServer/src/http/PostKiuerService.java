package http;

import integration.control.DAOControl;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import model.Helper;
import model.Kiuer;
import model.Place;
import model.PostKiuer;
import service.control.ParserControl;
import service.json.JSONParser;
import service.json.JSONParserFactory;
import service.security.MD5Crypt;
import util.DateFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by spronghi on 15/09/16.
 */
public class PostKiuerService extends HttpServlet {
    private DAO<PostKiuer> dao;
    private JSONParser<PostKiuer> parser;

    private PostKiuer getPostKiuer(HttpServletRequest request){
        Place place = DAOFactory.<Place>getInstance(DAOControl.PLACE).get(Integer.parseInt(request.getParameter("place_id")));
        if(place == null){
            place = new Place();
            place.setId(Integer.parseInt(request.getParameter("place_id")));
            place.setLocation(request.getParameter("location"));
            place.setAddress(request.getParameter("address"));
            place.setCity(request.getParameter("city"));
        }

        PostKiuer post = new PostKiuer();

        post.setId(Integer.parseInt(request.getParameter("id")));
        post.setKiuer(DAOFactory.<Kiuer>getInstance(DAOControl.KIUER).get(Integer.parseInt(request.getParameter("kiuer_id"))));
        post.setHelper(DAOFactory.<Helper>getInstance(DAOControl.HELPER).get(Integer.parseInt(request.getParameter("helper_id"))));
        post.setTitle(request.getParameter("title"));
        post.setStatus(request.getParameter("status"));
        post.setPlace(place);
        post.setStartDate(DateFormatter.fromRequest(request.getParameter("start")));
        post.setDuration(Integer.parseInt(request.getParameter("duration")));
        post.setCost(Double.parseDouble(request.getParameter("cost")));
        post.setOpen(Boolean.parseBoolean(request.getParameter("open")));
        return post;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = DAOFactory.getInstance(DAOControl.POST_KIUER);
        parser = JSONParserFactory.getInstance(ParserControl.POST_KIUER);

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
        PostKiuer post = getPostKiuer(request);
        dao.create(post);
        return parser.getJSONObj(post).toJSONString();
    }
    private String update(HttpServletRequest request){
        PostKiuer post = getPostKiuer(request);
        dao.update(post);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        PostKiuer post = getPostKiuer(request);
        dao.delete(post);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
}
