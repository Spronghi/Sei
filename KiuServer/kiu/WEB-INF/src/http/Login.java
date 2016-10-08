package http;

import integration.control.FilterControl;
import integration.dao.HelperDAO;
import integration.dao.KiuerDAO;
import model.Helper;
import model.Kiuer;
import service.control.ParserControl;
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
public class Login extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String userType = request.getParameter("type");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(userType.equals(HttpControl.HELPER)){
            Helper helper  = new HelperDAO().getAllBy(FilterControl.USERNAME, username).stream().filter(p->p.getUsername().equalsIgnoreCase(username)).findFirst().get();
            if(helper.getPassword().equals(MD5Crypt.crypt(password)))
                out.println(JSONParserFactory.<Helper>getInstance(ParserControl.HELPER).getJSONObj(helper).toJSONString());
        } else if(userType.equals(HttpControl.KIUER)){
            Kiuer kiuer  = new KiuerDAO().getAllBy(FilterControl.USERNAME, username).stream().filter(p->p.getUsername().equalsIgnoreCase(username)).findFirst().get();
            if(kiuer.getPassword().equals(MD5Crypt.crypt(password)))
                out.println(JSONParserFactory.<Kiuer>getInstance(ParserControl.KIUER).getJSONObj(kiuer).toJSONString());
        } else {
            out.println("no type found");
        }
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
