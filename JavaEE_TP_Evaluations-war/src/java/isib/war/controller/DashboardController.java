package isib.war.controller;

import isib.ejb.entity.Student;
import isib.ejb.entity.Student_Answer;
import isib.war.tools.Tools;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DashboardController extends BaseController {

    private static final Logger log4j = Logger.getLogger(DashboardController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);

        this.con = Tools.getConnexionSession(request);
        this.con.setModule_activate("Dashboard");

        request.setAttribute("comboBoxData", loadComboBoxData(
                this.con.getPerson().isIsStudent() ? this.con.getPerson().getId()
                : 0));

        Tools.redirectToPage(request, response, "views/dashboard/index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // -- Check if user is connected -- //
        Tools.checkIsConnected(request, response);

        try {
            this.con = Tools.getConnexionSession(request);
            int id_student = Integer.parseInt(request.getParameter("id_student"));
            
            Student student = (Student)studentEJB.read(id_student);
            
            if (student == null) {
                throw new Exception("Student not found!");
            }
            
            List<Student_Answer> student_answers = student_AnswerEJB.readAllByStudent(id_student);
            
            if (student_answers == null || student_answers.isEmpty()) {
                throw new Exception("Answers not found!");
            }
            
            request.setAttribute("student", student);
            request.setAttribute("student_answers", student_answers);
        } catch (Exception ex) {
            log4j.error(ex);
            request.setAttribute("error", "Not implemented");
        } finally {
            request.setAttribute(
                "comboBoxData",
                loadComboBoxData(
                    this.con.getPerson().isIsStudent()  ? this.con.getPerson().getId()
                                                        : 0
                )
            );
            
            Tools.redirectToPage(request, response, "views/dashboard/index.jsp");
        }

    }

    private Object[] loadComboBoxData(int id_student) {

        Object[] comboBoxData = new Object[1];

        comboBoxData[0] = id_student == 0 ? studentEJB.readAll()
                : new Object[]{studentEJB.read(id_student)};

        return comboBoxData;

    }

}
