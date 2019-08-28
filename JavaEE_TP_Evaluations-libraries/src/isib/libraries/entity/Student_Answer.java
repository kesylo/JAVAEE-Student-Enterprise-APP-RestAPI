/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.libraries.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
@Entity
@Table(name="students_answers")
public class Student_Answer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @Column(name="date")
    private Date date;
    
//    @Column(name="id_student")
//    private int id_student;
    
    @ManyToOne
    @JoinColumn(name = "id_student", unique = true)
    private Student student;
    
//    @Column(name="id_answer")
//    private int id_answer;
    
    @ManyToOne
    @JoinColumn(name = "id_answer", unique = true)
    private Answer answer;

    public Student_Answer() {
    }

    public Student_Answer(Date date, int id_student, int id_answer) {
        this.date = date;
//        this.id_student = id_student;
        this.student = new Student(id_student);
//        this.id_answer = id_answer;
        this.answer = new Answer(id_answer);
    }

    public Student_Answer(int id, Date date, int id_student, int id_answer) {
        this.id = id;
        this.date = date;
        this.student = new Student(id_student);
        this.answer = new Answer(id_answer);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    public int getId_student() {
//        return id_student;
//    }
//
//    public void setId_student(int id_student) {
//        this.id_student = id_student;
//    }
//
//    public int getId_answer() {
//        return id_answer;
//    }
//
//    public void setId_answer(int id_answer) {
//        this.id_answer = id_answer;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
