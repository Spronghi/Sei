package http;

import integration.control.DAOControl;
import integration.control.FilterControl;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import model.KiuingOperation;
import model.Operation;
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
import java.nio.file.OpenOption;
import java.util.List;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingOperationService extends HttpServlet {
    private DAO<KiuingOperation> dao;
    private JSONParser<KiuingOperation> parser;

    private KiuingOperation getKiuingOperation(HttpServletRequest request){
        KiuingOperation operation = new KiuingOperation();
        operation.setId(Integer.parseInt(request.getParameter("id")));
        operation.setKiuing(Integer.parseInt(request.getParameter("kiuing_id")));
        operation.setDone(Boolean.parseBoolean(request.getParameter("done")));

        List<Operation> operations = DAOFactory.<Operation>getFilterInstance(DAOControl.OPERATION).getAllBy(FilterControl.OPERATION,request.getParameter("operation"));
        operation.setOperation(operations.iterator().next());
        return operation;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        dao = DAOFactory.getInstance(DAOControl.KIUING_OPERATION);
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
}
