package isib.war.controller;

import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class ResultsTestController extends BaseController {

    private static final Logger log4j = Logger.getLogger(ResultsTestController.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);
        
        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate("ResultsTest");
                
        Tools.redirectToPage(request, response, "views/resultsTest/index.jsp");
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
//        if ("getDataById".equals(action)) {
//            getDataById(request, response);
//        } else if ("create".equals(action)) {
//            create(request, response);
//        } else if ("update".equals(action)) {
//            update(request, response);
//        } else if ("delete".equals(action)) {
//            delete(request, response);
//        }
        
    }
    
}
