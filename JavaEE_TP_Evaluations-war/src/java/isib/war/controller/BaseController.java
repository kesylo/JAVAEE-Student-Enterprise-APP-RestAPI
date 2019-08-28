package isib.war.controller;

import isib.ejb.entity.*;
import isib.war.bo.*;
import com.google.gson.Gson;
import isib.ejb.services.interfaces.*;
import isib.war.tools.Tools;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class BaseController extends HttpServlet {

    private static final Logger log4j = Logger.getLogger(BaseController.class);
    
    @EJB(beanName = "UserEJB")
    protected IUsersServices userEJB;
    
    @EJB(beanName = "TeacherEJB")
    protected IChildrenUserServices teacherEJB;
    
    @EJB(beanName = "Student_AnswerEJB")
    protected IStudent_AnswerServices student_AnswerEJB;
    
    @EJB(beanName = "StudentEJB")
    protected IChildrenUserServices studentEJB;
    
    @EJB(beanName = "QuestionEJB")
    protected IQuestionServices questionEJB;
    
    @EJB(beanName = "EvaluationEJB")
    protected IServices evaluationEJB;
    
    @EJB(beanName = "AnswerEJB")
    protected IAnswerServices answerEJB;
    
    protected Notification notification = new Notification(false);
    protected Connexion con;
    
    protected void sendNotificationToResponse(HttpServletResponse response) throws IOException{
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
            new Gson().toJson(this.notification)
        );
        
    }
    
    protected void sendNotificationToResponse(HttpServletResponse response, Object data) throws IOException{
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
            new Gson().toJson(data)
        );
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action;
        
        action = request.getParameter("action");
        
        if ("dataTablesValue".equals(action)) {
            dataTablesLoading(request, response);
        }
        
    }
    
    
    
    private void dataTablesLoading(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        String module;
        List<DataTableRow> obj = new ArrayList<>();
        int index = 0;
        
        try {
            module = request.getParameter("module");
            
            switch(module){
                case "Evaluation":  
                    for(Object data : evaluationEJB.readAll())
                    {
                        obj.add(
                            new DataTableRow(
                                ((Evaluation)data).getId() + "", 
                                Tools.htmlCheckboxTable(((Evaluation)data).getId(), "evaluation"),
                                (++index) + "",
                                ((Evaluation)data).getCode(), 
                                ((Evaluation)data).getTitle(),
                                ((Evaluation)data).getDuration() + " min",
                                Tools.htmlBoutonDisplayList(
                                    ((Evaluation)data).getId(), 
                                    ((Evaluation)data).getQuestions().size(),
                                    "question(s)"
                                ),
                                Tools.htmlBoutonActionTable(((Evaluation)data).getId())
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                case "Question":  
                    for(Object data : questionEJB.readAll())
                    {
                        obj.add(
                            new DataTableRow(
                                ((Question)data).getId() + "", 
                                Tools.htmlCheckboxTable(((Question)data).getId(), "question"),
                                (++index) + "",
                                ((Question)data).getCode(), 
                                ((Question)data).getTitle(),
                                ((Question)data).getMarks() + "",
                                ((Question)data).getEvaluation().getTitle(),
                                Tools.htmlBoutonDisplayList(
                                    ((Question)data).getId(), 
                                    ((Question)data).getAnswers().size(),
                                    "answer(s)"
                                ),
                                Tools.htmlBoutonActionTable(((Question)data).getId())
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                case "QuestionByEvaluation":  
                    int id_evaluation = Integer.parseInt(request.getParameter("id_evaluation"));
                    for(Object data : questionEJB.readAllByEvaluation(id_evaluation))
                    {
                        obj.add(
                            new DataTableRow(
                                ((Question)data).getId() + "", 
                                (++index) + "",
                                ((Question)data).getCode(), 
                                ((Question)data).getTitle(),
                                ((Question)data).getMarks() + "",
                                (index == 1) ? ((Question)data).getEvaluation().getTitle() 
                                             : null
                            )
                        );
                    }
                    if (obj.size() == 0) {
                        throw new Exception("Empty!");
                    }                    
                    this.notification =  new Notification(obj);
                    break;
                case "Answer":  
                    for(Object data : answerEJB.readAll())
                    {
                        obj.add(
                            new DataTableRow(
                                ((Answer)data).getId() + "", 
                                Tools.htmlCheckboxTable(((Answer)data).getId(), "answer"),
                                (++index) + "",
                                ((Answer)data).getCode(), 
                                ((Answer)data).getTitle(),
                                ((Answer)data).isTruth() ? "<i class=\"fa fa-check\"></i> Correct" 
                                                         : "<i class=\"fa fa-remove\"></i> Incorrect",
                                ((Answer)data).getQuestion().getTitle(),
                                Tools.htmlBoutonActionTable(((Answer)data).getId())
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                case "AnswerByQuestion":  
                    int id_question = Integer.parseInt(request.getParameter("id_question"));
                    for(Object data : answerEJB.readAllByQuestion(id_question))
                    {
                        obj.add(
                            new DataTableRow(
                                ((Answer)data).getId() + "", 
                                (++index) + "",
                                ((Answer)data).getCode(), 
                                ((Answer)data).getTitle(),
                                ((Answer)data).isTruth() ? "<i class=\"fa fa-check\"></i> Correct"
                                                         : "<i class=\"fa fa-remove\"></i> Incorrect",
                                (index == 1) ? ((Answer)data).getQuestion().getTitle() 
                                             : null
                            )
                        );
                    }
                    if (obj.size() == 0) {
                        throw new Exception("Empty!");
                    }  
                    this.notification = new Notification(obj);
                    break;
                case "User":  
                    for(Object data : userEJB.readAll())
                    {
                        boolean isStudent = false;
                        Object person;
                        person = studentEJB.readIdUser(((User)data).getId());
                        if (person == null) {
                            person = teacherEJB.readIdUser(((User)data).getId());
                            if (person != null) {
                                isStudent = false;
                            }
                        } else {
                            isStudent = true;
                        }
                        
                        obj.add(
                            new DataTableRow(
                                ((User)data).getId() + "", 
                                Tools.htmlCheckboxTable(((User)data).getId(), "user"),
                                (++index) + "",
                                ((User)data).getLogin(), 
                                "***** - ***** - *****",
                                ((User)data).isEnabled() ? "<i class=\"fa fa-unlock\"></i> Enabled" 
                                                         : "<i class=\"fa fa-lock\"></i> Disabled",
                                (person != null) ? Tools.htmlBoutonPerson(((User)data).getId(), person, isStudent) 
                                                 : "Nothing",
                                Tools.htmlBoutonActionTable(((User)data).getId())
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                case "Student":  
                    for(Object data : studentEJB.readAll())
                    {
                        obj.add(
                            new DataTableRow(
                                ((Student)data).getId() + "",
                                Tools.htmlCheckboxTable(((Student)data).getId(), "student"),
                                (++index) + "",
                                ((Student)data).getMatricule(),
                                ((Student)data).getFirstname(),
                                ((Student)data).getLastname(),
                                ((Student)data).getAddress(),
                                ((Student)data).getMobile_phone(),
                                ((Student)data).getEmail(),
                                Tools.convertToString(((Student)data).getBirthday()),
                                ((Student)data).getUser().getLogin(),
                                Tools.htmlBoutonActionTable(((Student)data).getId())
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                case "Teacher":  
                    for(Object data : teacherEJB.readAll())
                    {
                        obj.add(
                            new DataTableRow(
                                ((Teacher)data).getId() + "",
                                Tools.htmlCheckboxTable(((Teacher)data).getId(), "teacher"),
                                (++index) + "",
                                ((Teacher)data).getMatricule(),
                                ((Teacher)data).getFirstname(),
                                ((Teacher)data).getLastname(),
                                ((Teacher)data).getAddress(),
                                ((Teacher)data).getMobile_phone(),
                                ((Teacher)data).getEmail(),
                                Tools.convertToString(((Teacher)data).getBirthday()),
                                ((Teacher)data).getUser().getLogin(),
                                Tools.htmlBoutonActionTable(((Teacher)data).getId())
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                case "Student_Answer":  
                    for(Object data : student_AnswerEJB.readAll())
                    {
                        obj.add(
                            new DataTableRow(
                                ((Student_Answer)data).getId() + "", 
                                Tools.htmlCheckboxTable(((Student_Answer)data).getId(), "student_answer"),
                                (++index) + "",
                                ((Student_Answer)data).getStudent().getMatricule(), 
                                ((Student_Answer)data).getStudent().getFirstname(),
                                ((Student_Answer)data).getStudent().getLastname(),
                                ((Student_Answer)data).getAnswer().getQuestion().getEvaluation().getTitle(),
                                ((Student_Answer)data).getAnswer().getQuestion().getTitle(),
                                ((Student_Answer)data).getAnswer().getTitle(),
                                ((Student_Answer)data).getAnswer().getQuestion().getMarks() + "",
                                ((Student_Answer)data).getAnswer().isTruth() ? "<i class=\"fa fa-check text-success\"></i>" 
                                                                             : "<i class=\"fa fa-remove text-danger\"></i>"
                            )
                        );
                    }
                    this.notification = new Notification(obj);
                    break;
                default:
                    break;
            }
        } 
        catch(Exception ex) {
            log4j.error(ex);
            this.notification = new Notification(false, ex.getMessage());
        }
        finally{
            sendNotificationToResponse(response);
        }
        
    }
    
}
