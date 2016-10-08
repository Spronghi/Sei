package http;

import integration.control.FilterControl;
import integration.dao.KiuingOperationDAO;
import integration.dao.OperationDAO;
import model.KiuingOperation;
import model.Operation;
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
public class KiuingOperationService extends HttpServlet {
    private KiuingOperationDAO dao;
    private JSONParser<KiuingOperation> parser;

    private KiuingOperation getKiuingOperation(HttpServletRequest request){
        KiuingOperation operation = new KiuingOperation();
        operation.setId(Integer.parseInt(request.getParameter("id")));
        operation.setKiuing(Integer.parseInt(request.getParameter("kiuing_id")));
        operation.setDone(Boolean.parseBoolean(request.getParameter("done")));

        List<Operation> operations = new OperationDAO().getAllBy(FilterControl.OPERATION,request.getParameter("operation"));
        operation.setOperation(operations.iterator().next());
        return operation;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = new KiuingOperationDAO();
        parser = JSONParserFactory.getInstance(ParserControl.KIUING_OPERATION);

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
            case HttpControl.KIUING:
                responseString = getAllByKiuing(request);
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
        KiuingOperation operation = getKiuingOperation(request);
        dao.create(operation);
        return parser.getJSONObj(operation).toJSONString();
    }
    private String update(HttpServletRequest request){
        KiuingOperation operation = getKiuingOperation(request);
        dao.update(operation);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String delete(HttpServletRequest request){
        KiuingOperation operation = getKiuingOperation(request);
        dao.delete(operation);
        return JSONParser.getSuccessJSON(true).toJSONString();
    }
    private String get(HttpServletRequest request){
        return parser.getJSONObj(dao.get(Integer.parseInt(request.getParameter("id")))).toJSONString();
    }
    private String getAll(){
        return parser.getJSONArr(dao.getAll()).toJSONString();
    }
    private String getAllByKiuing(HttpServletRequest request){
        return parser.getJSONArr(dao.getAllBy(FilterControl.KIUING, request.getParameter("kiuing_id"))).toJSONString();
    }
}
