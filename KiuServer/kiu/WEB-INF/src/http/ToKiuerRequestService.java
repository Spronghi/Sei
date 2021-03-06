package http;

import integration.control.FilterControl;
import integration.dao.*;
import model.*;
import service.control.ParserControl;
import service.json.JSONParser;
import service.json.JSONParserFactory;

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
public class ToKiuerRequestService extends HttpServlet {
    private ToKiuerRequestDAO dao;
    private JSONParser<ToKiuerRequest> parser;

    private ToKiuerRequest getToKiuerRequest(HttpServletRequest request){
        ToKiuerRequest toKiuerRequest = new ToKiuerRequest();

        toKiuerRequest.setId(Integer.parseInt(request.getParameter("id")));
        toKiuerRequest.setSeen(Boolean.parseBoolean(request.getParameter("seen")));
        toKiuerRequest.setSender(new HelperDAO().get(Integer.parseInt(request.getParameter("sender_id"))));
        toKiuerRequest.setAddressee(new KiuerDAO().get(Integer.parseInt(request.getParameter("addressee_id"))));
        toKiuerRequest.setPost(new PostKiuerDAO().get(Integer.parseInt(request.getParameter("post_id"))));

        List<RequestType> type = new RequestTypeDAO().getAllBy(FilterControl.TYPE,request.getParameter("type"));
        toKiuerRequest.setType(type.stream().filter(p->p.getType().equals(request.getParameter("type"))).findFirst().get());

        return toKiuerRequest;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = new ToKiuerRequestDAO();
        parser = JSONParserFactory.getInstance(ParserControl.TO_KIUER_REQUEST);

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
            case HttpControl.ADDRESSEE:
                responseString = getAllByAddressee(request);
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
        ToKiuerRequest toKiuerRequest = getToKiuerRequest(request);
        dao.create(toKiuerRequest);
        return parser.getJSONObj(toKiuerRequest).toJSONString();
    }
    private String update(HttpServletRequest request){
        ToKiuerRequest toKiuerRequest = getToKiuerRequest(request);
        dao.update(toKiuerRequest);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        ToKiuerRequest toKiuerRequest = getToKiuerRequest(request);
        dao.delete(toKiuerRequest);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
    private String getAllByAddressee(HttpServletRequest request){
        return parser.getJSONArr(dao.getAllBy(FilterControl.KIUER, request.getParameter("addressee_id"))).toJSONString();
    }
}
