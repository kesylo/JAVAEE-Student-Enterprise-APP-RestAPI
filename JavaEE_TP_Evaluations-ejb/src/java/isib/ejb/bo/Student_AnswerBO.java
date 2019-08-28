/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
public class Student_AnswerBO extends BO implements Serializable {
    
    private Date date;
    private StudentBO student;
    private AnswerBO answer;

    public Student_AnswerBO() {
    }

    public Student_AnswerBO(Date date, int id_student, int id_answer) {
        this.date = date;
        this.student = new StudentBO(id_student);
        this.answer = new AnswerBO(id_answer);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AnswerBO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBO answer) {
        this.answer = answer;
    }

    public StudentBO getStudent() {
        return student;
    }

    public void setStudent(StudentBO student) {
        this.student = student;
    }
}
