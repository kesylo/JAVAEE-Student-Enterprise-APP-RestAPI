package isib.war.controller;

import isib.ejb.entity.Student;
import isib.ejb.entity.Teacher;
import isib.ejb.entity.User;
import isib.war.bo.DataTableRow;
import isib.war.bo.Notification;
import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class UserController extends BaseController {

    private static final Logger log4j = Logger.getLogger(UserController.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);
        
        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate("User");
//        this.con.setComboBoxData(
//            loadComboBoxData()
//        );
                
        Tools.redirectToPage(request, response, "views/user/index.jsp");
        
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
            obj = userEJB.read(
                Integer.parseInt(request.getParameter("id"))
            );
            
            if (obj != null){
                boolean isStudent = false;
                Object person;
                person = studentEJB.readIdUser(((User) obj).getId());
                if (person == null) {
                    person = teacherEJB.readIdUser(((User) obj).getId());
                    if (person != null) {
                        isStudent = false;
                    }
                } else {
                    isStudent = true;
                }

                this.notification = new Notification(
                                        new DataTableRow(
                                                ((User)obj).getId() + "",
                                                ((User)obj).getLogin(),
                                                ((User)obj).getPassword(),
                                                ((User)obj).isEnabled() + "",
                                                (person != null && isStudent) ? ((Student)person).getId() + "" 
                                                                              : "",
                                                (person != null && !isStudent) ? ((Teacher)person).getId() + "" 
                                                                               : ""
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
//            Integer id_student = Integer.parseInt(request.getParameter("id_student"));
//            Integer id_teacher = Integer.parseInt(request.getParameter("id_teacher"));
            
            obj = userEJB.create(
                        new User(
                            request.getParameter("login"), 
                            request.getParameter("password"),
                            Boolean.parseBoolean(request.getParameter("enabled"))
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
            obj = userEJB.update(
                        new User(
                            Integer.parseInt(request.getParameter("id")), 
                            request.getParameter("login"), 
                            request.getParameter("password"),
                            Boolean.parseBoolean(request.getParameter("enabled"))
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
            obj = userEJB.delete(Integer.parseInt(request.getParameter("id")));
            
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
    
//    private Object[] loadComboBoxData(){
//    
//        Object[] comboBoxData = new Object[2];
//        
//        comboBoxData[0] = studentEJB.readAll();
//        comboBoxData[1] = teacherEJB.readAll();
//        
//        return comboBoxData;
//        
//    }
    
}
