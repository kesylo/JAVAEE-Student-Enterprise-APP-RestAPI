package isib.war.controller;

import isib.ejb.entity.Student;
import isib.ejb.entity.Teacher;
import isib.war.bo.Connexion;
import isib.war.bo.Notification;
import isib.war.tools.Tools;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AuthenticationController extends BaseController {

    private static final Logger log4j = Logger.getLogger(AuthenticationController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate(null);
        
        Tools.redirectToPage(request, response, "views/authentication/index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("login".equals(action)) {
            login(request, response);
        } else if ("logout".equals(action)) {
            logout(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login, password;
        Object obj;
        Boolean isStudent = null;

        try {
            login = request.getParameter("login");
            password = request.getParameter("password");

            // -- try found in Student -- //
            obj = studentEJB.read(login, password);

            if (obj == null) {
                // -- Try found in Teacher -- //
                obj = teacherEJB.read(login, password);
                if (obj != null) {
                    isStudent = false;
                }
            } else {
                isStudent = true;
            }

            if (obj != null) {
                if (userEJB.read(login, password, true) == null) {
                    throw new Exception("The account has been disabled!");
                }
                
                this.con = Tools.getConnexionSession(request);

                if (isStudent) {
                    this.con.setPerson((Student) obj);
                } else {
                    this.con.setPerson((Teacher) obj);
                }

                this.notification = new Notification("Dashboard");
            } else {
                this.notification = new Notification(false, "Login or password was wrong!");
            }
        } catch (Exception ex) {
            log4j.error(ex);
            this.notification = new Notification(false, ex.getMessage());
        } finally {
            sendNotificationToResponse(response);
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            this.con = Tools.getConnexionSession(request);

            this.con = new Connexion();

            this.notification = new Notification("Authentication");
        } catch (Exception ex) {
            log4j.error(ex);
        } finally {
            sendNotificationToResponse(response);
        }

    }
}
