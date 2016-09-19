package http;

import integration.control.DAOControl;
import integration.control.FilterControl;
import integration.dao.DAO;
import integration.dao.DAOFactory;
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
public class ToHelperRequestService extends HttpServlet {
    private DAO<ToHelperRequest> dao;
    private JSONParser<ToHelperRequest> parser;

    private ToHelperRequest getToHelperRequest(HttpServletRequest request){
        ToHelperRequest toKiuerRequest = new ToHelperRequest();

        toKiuerRequest.setId(Integer.parseInt(request.getParameter("id")));
        toKiuerRequest.setSeen(Boolean.parseBoolean(request.getParameter("seen")));
        toKiuerRequest.setAddressee(DAOFactory.<Helper>getInstance(DAOControl.HELPER).get(Integer.parseInt(request.getParameter("addressee_id"))));
        toKiuerRequest.setSender(DAOFactory.<Kiuer>getInstance(DAOControl.KIUER).get(Integer.parseInt(request.getParameter("sender_id"))));
        toKiuerRequest.setPost(DAOFactory.<PostKiuer>getInstance(DAOControl.POST_KIUER).get(Integer.parseInt(request.getParameter("post_id"))));

        List<RequestType> type = DAOFactory.<RequestType>getFilterInstance(DAOControl.REQUEST_TYPE).getAllBy(FilterControl.TYPE,request.getParameter("type"));
        toKiuerRequest.setType(type.iterator().next());

        return toKiuerRequest;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = DAOFactory.getInstance(DAOControl.TO_HELPER_REQUEST);
        parser = JSONParserFactory.getInstance(ParserControl.TO_HELPER_REQUEST);

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
        ToHelperRequest toKiuerRequest = getToHelperRequest(request);
        dao.create(toKiuerRequest);
        return parser.getJSONObj(toKiuerRequest).toJSONString();
    }
    private String update(HttpServletRequest request){
        ToHelperRequest toKiuerRequest = getToHelperRequest(request);
        dao.update(toKiuerRequest);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        ToHelperRequest toKiuerRequest = getToHelperRequest(request);
        dao.delete(toKiuerRequest);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
}
