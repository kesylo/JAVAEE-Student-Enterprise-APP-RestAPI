/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.dao.Student_AnswerDAO;
import isib.ejb.entity.Answer;
import isib.ejb.entity.Student_Answer;
import isib.ejb.services.interfaces.IStudent_AnswerServices;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "Student_AnswerEJB")
public class Students_AnswersServices implements IStudent_AnswerServices {

    @Override
    public Student_AnswerDAO dao() {
        return new Student_AnswerDAO();
    }
    
    @Override
    public Student_Answer create(Student_Answer obj) {
        return dao().create(obj);
    }

    @Override
    public Student_Answer createWithRelation(Student_Answer obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public Student_Answer update(Student_Answer obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public Student_Answer read(int id) {
        return dao().get(id);
    }

    @Override
    public List<Student_Answer> readAll() {
        return dao().getAll();
    }

    @Override
    public Student_Answer read(int id_student, int id_answer) {
        return dao().get(id_student, id_answer);
    }

    @Override
    public Student_Answer readByAnswer(int id_answer) {
        return dao().getByAnswer(id_answer);
    }

    @Override
    public List<Student_Answer> readAllByStudent(int id_student) {
        return dao().readAllByStudent(id_student);
    }

    @Override
    public boolean create(Student_Answer[] obj) {
        return dao().create(obj);
    }

    @Override
    public List<Student_Answer> getAnswers(int id_student, int id_evaluation) {
        return dao().getAnswers(id_student, id_evaluation);
    }
}
