/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.war.bo;

import isib.ejb.entity.Student;
import isib.ejb.entity.Teacher;
import isib.ejb.bo.TeacherBO;
import isib.ejb.bo.UserBO;
import isib.ejb.bo.PersonBO;
import isib.ejb.bo.StudentBO;
import isib.ejb.entity.Answer;
import isib.ejb.entity.Evaluation;
import isib.ejb.entity.Question;
import isib.ejb.entity.Student_Answer;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author LOIC
 */
public class Connexion {
    
    private String id_session;
    private PersonBO person;
    private String module_activate;
//    private Object[] comboBoxData;
    private Date connection_date;

    public Connexion() {
        this.connection_date = new Date();
    }

    public Connexion(String id_session) {
        this.id_session = id_session;
        this.connection_date = new Date();
    }

    public String getId_session() {
        return id_session;
    }

    public void setId_session(String id_session) {
        this.id_session = id_session;
    }

    public PersonBO getPerson() {
        return person;
    }

    public String getModule_activate() {
        return module_activate;
    }

    public void setModule_activate(String module_activate) {
        this.module_activate = module_activate;
    }

    public void setPerson(PersonBO person) {
        this.person = person;
    }

    public void setPerson(Teacher person) {
        this.person =
            new TeacherBO(
                    person.getId(),
                    new UserBO(
                        person.getUser().getId(),
                        person.getUser().getLogin(),
                        person.getUser().getPassword(),
                        person.getUser().isEnabled()
                    ),
                    person.getMatricule(),
                    person.getFirstname(),
                    person.getLastname(),
                    person.getAddress(),
                    person.getMobile_phone(),
                    person.getEmail(),
                    person.getBirthday()
            );
    }

    public void setPerson(Student person) {
        this.person =
            new StudentBO(
                    person.getId(),
                    new UserBO(
                        person.getUser().getId(),
                        person.getUser().getLogin(),
                        person.getUser().getPassword(),
                        person.getUser().isEnabled()
                    ),
                    person.getMatricule(),
                    person.getFirstname(),
                    person.getLastname(),
                    person.getAddress(),
                    person.getMobile_phone(),
                    person.getEmail(),
                    person.getBirthday()
            );
    }     

//    public Object[] getComboBoxData() {
//        return comboBoxData;
//    }
//
//    public void setComboBoxData(Object[] comboBoxData) {
//        this.comboBoxData = comboBoxData;
//    }

    public Date getConnection_date() {
        return connection_date;
    }

    public void setConnection_date(Date connection_date) {
        this.connection_date = connection_date;
    }
    
    public boolean isAuthentificated(){
        
        return
            this.person != null && this.person.getId() != 0;
    }
    
    public int getAge(Date birthday){
        
        return 
            Period.between(
                birthday.toInstant().atZone(
                    ZoneId.systemDefault()
                ).toLocalDate(), 
                LocalDate.now()
            ).getYears();
            
    }
    
    public LazyObject getLazyObjectEvaluation(List<Student_Answer> student_answers){
        
        Set<Evaluation> evaluations = new HashSet<>();
        int maxMarks = 0;
        int hisMarks = 0;
        
        for(Student_Answer val : student_answers) {
            if (val.getAnswer().isTruth()) {
                System.out.println("Answer: " + val.getAnswer().getTitle() + ", Question : " + val.getAnswer().getQuestion().getTitle() + ", marks: " + val.getAnswer().getQuestion().getMarks());
                hisMarks += val.getAnswer().getQuestion().getMarks();
            }
            maxMarks += val.getAnswer().getQuestion().getMarks();
            evaluations.add(val.getAnswer().getQuestion().getEvaluation());
        }
        
        return 
            new LazyObject(
                evaluations, 
                (maxMarks / 2 <= hisMarks)  ? "<i class=\"fa fa-check-circle fa-2x  text-success\"></i>" 
                                            : "<i class=\"fa fa-remove fa-2x  text-danger\"></i>", 
                hisMarks, 
                maxMarks
            );
            
    }
    
    public boolean isAswered(int id_question, List<Student_Answer> student_answers){
        
        boolean exist = false;
        
        for(Student_Answer val : student_answers) {
            if (val.getAnswer().getQuestion().getId() == id_question){
                exist = true;
                break;
            }
        }
        System.out.println("id_question: " + id_question + ", exist: " + exist);
        
        return exist;
            
    }
    
    public Answer getAswered(Set<Answer> answers, List<Student_Answer> student_answers){
                
        for(Answer answer : answers) {
            for(Student_Answer val : student_answers) {
                if (val.getAnswer().getId() == answer.getId()){
                    return answer;
                }
            }
        }
        
        return null;
            
    }
}