package isib.war.controller;

import isib.ejb.entity.Question;
import isib.war.bo.DataTableRow;
import isib.war.bo.Notification;
import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class QuestionController extends BaseController {

    private static final Logger log4j = Logger.getLogger(QuestionController.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);
        
        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate("Question");
        
        request.setAttribute("comboBoxData", loadComboBoxData());
                
        Tools.redirectToPage(request, response, "views/question/index.jsp");
        
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
            obj = questionEJB.read(
                Integer.parseInt(request.getParameter("id"))
            );
            
            if (obj != null){
                this.notification = new Notification(
                    new DataTableRow(
                        ((Question)obj).getId() + "",
                        ((Question)obj).getCode(),
                        ((Question)obj).getTitle(),
                        ((Question)obj).getMarks() + "",
                        ((Question)obj).getEvaluation().getId() + ""
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
            obj = questionEJB.create(
                        new Question(
                            Integer.parseInt(request.getParameter("id_evaluation")),
                            request.getParameter("code"), 
                            request.getParameter("title"),
                            Integer.parseInt(request.getParameter("marks"))
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
            obj = questionEJB.update(
                        new Question(
                            Integer.parseInt(request.getParameter("id")), 
                            Integer.parseInt(request.getParameter("id_evaluation")),
                            request.getParameter("code"), 
                            request.getParameter("title"),
                            Integer.parseInt(request.getParameter("marks"))
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
            obj = questionEJB.delete(Integer.parseInt(request.getParameter("id")));
            
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
        
        comboBoxData[0] = evaluationEJB.readAll();
        
        return comboBoxData;
        
    }
    
}
