/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services.interfaces;

import isib.ejb.dao.IDAO;
import isib.ejb.dao.Student_AnswerDAO;
import isib.ejb.entity.Answer;
import isib.ejb.entity.Student_Answer;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author LOIC
 */
public interface IStudent_AnswerServices {
    
    Student_AnswerDAO dao();
    Student_Answer create(Student_Answer obj);
    boolean create(Student_Answer[] obj);
    Student_Answer createWithRelation(Student_Answer obj);
    Student_Answer update(Student_Answer obj);
    boolean delete(int id);
    List<Student_Answer> getAnswers(int id_student, int id_evaluation);
    Student_Answer read(int id);
    Student_Answer read(int id_student, int id_answer);
    Student_Answer readByAnswer(int id_answer);
    List<Student_Answer> readAll();
    List<Student_Answer> readAllByStudent(int id_student);
    
}
