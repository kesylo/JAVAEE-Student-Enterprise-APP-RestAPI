package isib.war.controller;

import isib.ejb.entity.Answer;
import isib.war.bo.DataTableRow;
import isib.war.bo.Notification;
import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class AnswerController extends BaseController {

    private static final Logger log4j = Logger.getLogger(AnswerController.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);
        
        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate("Answer");
        
        request.setAttribute("comboBoxData", loadComboBoxData());
                
        Tools.redirectToPage(request, response, "views/answer/index.jsp");
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("getDataById".equals(action)) {
            getDataById(request, response);
        } else if ("create".equals(action)) {
            create(request, response);
        } else if ("update".equals(action)) {
            update(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        }
        
    }
    
    private void getDataById(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        int id;
        Object obj;
        
        try {
            obj = answerEJB.read(
                Integer.parseInt(request.getParameter("id"))
            );
            
            if (obj != null){
                this.notification = new Notification(
                                        new DataTableRow(
                                            ((Answer)obj).getId() + "",
                                            ((Answer)obj).getCode(),
                                            ((Answer)obj).getTitle(),
                                            ((Answer)obj).isTruth() + "",
                                            ((Answer)obj).getQuestion().getId() + ""
                                        )
                                    );
            } else {
                throw new Exception("Data not found");
            }
        } 
        catch(Exception ex) {
            log4j.error(ex);
            this.notification = new Notification(false);
        }
        finally{
            sendNotificationToResponse(response);
        }
        
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        Object obj;
        
        try {
            obj = answerEJB.create(
                        new Answer(
                            Integer.parseInt(request.getParameter("id_question")),
                            request.getParameter("code"), 
                            request.getParameter("title"),
                            Boolean.parseBoolean(request.getParameter("truth"))
                        )
                    );
            
            if (obj != null){
                this.notification = new Notification(obj);
            } else {
                throw new Exception("Data not found");
            }
        } 
        catch(Exception ex) {
            log4j.error(ex);
            this.notification = new Notification(false);
        }
        finally{
            sendNotificationToResponse(response);
        }
        
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        Object obj;
        
        try {
            obj = answerEJB.update(
                        new Answer(
                            Integer.parseInt(request.getParameter("id")), 
                            Integer.parseInt(request.getParameter("id_question")),
                            request.getParameter("code"), 
                            request.getParameter("title"),
                            Boolean.parseBoolean(request.getParameter("truth"))
                        )
                    );
            
            if (obj != null){
                this.notification = new Notification(obj);
            } else {
                throw new Exception("Data not found");
            }
        } 
        catch(Exception ex) {
            log4j.error(ex);
            this.notification = new Notification(false);
        }
        finally{
            sendNotificationToResponse(response);
        }
        
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        Object obj;
        
        try {
            obj = answerEJB.delete(Integer.parseInt(request.getParameter("id")));
            
            this.notification = new Notification(obj);
        } 
        catch(Exception ex) {
            log4j.error(ex);
            this.notification = new Notification(false);
        }
        finally{
            sendNotificationToResponse(response);
        }
        
    }
    
    private Object[] loadComboBoxData(){
    
        Object[] comboBoxData = new Object[1];
        
        comboBoxData[0] = questionEJB.readAll();
        
        return comboBoxData;
        
    }
    
}
