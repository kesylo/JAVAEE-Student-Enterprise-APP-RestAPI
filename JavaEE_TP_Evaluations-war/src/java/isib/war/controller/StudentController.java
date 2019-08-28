package isib.war.controller;

import isib.ejb.entity.Student;
import isib.war.bo.Notification;
import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class StudentController extends BaseController {

    private static final Logger log4j = Logger.getLogger(StudentController.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);
        
        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate("Student");
        
        request.setAttribute("comboBoxData", loadComboBoxData());
                
        Tools.redirectToPage(request, response, "views/student/index.jsp");
        
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
            obj = studentEJB.read(
                Integer.parseInt(request.getParameter("id"))
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
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        Object obj;
        
        try {
            obj = studentEJB.create(
                        new Student(
                            Integer.parseInt(request.getParameter("id")), 
                            Integer.parseInt(!"".equals(request.getParameter("id_user")) ? request.getParameter("id_user") 
                                                                                         : "0"),
                            request.getParameter("matricule"), 
                            request.getParameter("firstname"), 
                            request.getParameter("lastname"), 
                            request.getParameter("address"), 
                            request.getParameter("mobile_phone"), 
                            request.getParameter("email"), 
                            Tools.convertToDate(request.getParameter("birthday"))
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
            obj = studentEJB.update(
                        new Student(
                            Integer.parseInt(request.getParameter("id")), 
                            Integer.parseInt(!"".equals(request.getParameter("id_user")) ? request.getParameter("id_user") 
                                                                                         : "0"),
                            request.getParameter("matricule"), 
                            request.getParameter("firstname"), 
                            request.getParameter("lastname"), 
                            request.getParameter("address"), 
                            request.getParameter("mobile_phone"), 
                            request.getParameter("email"), 
                            Tools.convertToDate(request.getParameter("birthday"))
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
            obj = studentEJB.delete(Integer.parseInt(request.getParameter("id")));
            
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
        
        comboBoxData[0] = userEJB.readAll();
        
        return comboBoxData;
        
    }
    
}
