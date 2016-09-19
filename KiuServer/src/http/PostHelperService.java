package http;

import integration.dao.PostHelperDAO;
import integration.dao.HelperDAO;
import model.PostHelper;
import service.control.ParserControl;
import service.json.JSONParser;
import service.json.JSONParserFactory;
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
public class PostHelperService extends HttpServlet {
    private PostHelperDAO dao;
    private JSONParser<PostHelper> parser;

    private PostHelper getPostHelper(HttpServletRequest request){
        PostHelper post = new PostHelper();

        post.setId(Integer.parseInt(request.getParameter("id")));
        post.setHelper(new HelperDAO().get(Integer.parseInt(request.getParameter("helper_id"))));
        post.setTitle(request.getParameter("title"));
        post.setStartDate(DateFormatter.fromRequest(request.getParameter("start")));
        post.setEndDate(DateFormatter.fromRequest(request.getParameter("end")));
        post.setCity(request.getParameter("city"));
        post.setCost(Double.parseDouble(request.getParameter("cost")));
        post.setOpen(Boolean.parseBoolean(request.getParameter("open")));
        return post;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = new PostHelperDAO();
        parser = JSONParserFactory.getInstance(ParserControl.POST_HELPER);

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
        PostHelper post = getPostHelper(request);
        dao.create(post);
        return parser.getJSONObj(post).toJSONString();
    }
    private String update(HttpServletRequest request){
        PostHelper post = getPostHelper(request);
        dao.update(post);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        PostHelper post = getPostHelper(request);
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
