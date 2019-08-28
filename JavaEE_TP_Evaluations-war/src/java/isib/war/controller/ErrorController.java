package isib.war.controller;

import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // -- Set request variable -- //
        request.setAttribute("codeError", response.getStatus());
        
        Tools.redirectToPage(request, response, "views/error/index.jsp");

    }
}
