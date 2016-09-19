package http;

import integration.dao.KiuingDAO;
import integration.dao.PostKiuerDAO;
import model.Kiuing;
import model.PostKiuer;
import service.control.ParserControl;
import service.json.JSONParser;
import service.json.JSONParserFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingService extends HttpServlet {
    private KiuingDAO dao;
    private JSONParser<Kiuing> parser;

    private Kiuing getKiuing(HttpServletRequest request){
        Kiuing kiuing = new Kiuing();
        kiuing.setId(Integer.parseInt(request.getParameter("id")));
        kiuing.setPost(new PostKiuerDAO().get(Integer.parseInt(request.getParameter("post_id"))));
        return kiuing;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = new KiuingDAO();
        parser = JSONParserFactory.getInstance(ParserControl.KIUING);

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
        Kiuing kiuing = getKiuing(request);
        dao.create(kiuing);
        return parser.getJSONObj(kiuing).toJSONString();
    }
    private String update(HttpServletRequest request){
        Kiuing kiuing = getKiuing(request);
        dao.update(kiuing);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        Kiuing kiuing = getKiuing(request);
        dao.delete(kiuing);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
}
