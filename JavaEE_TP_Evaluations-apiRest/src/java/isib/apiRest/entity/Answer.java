/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.apiRest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
@Entity
@Table(name="answers")
public class Answer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
        
    @Column(name="code", unique=true)
    private String code;
    
    @Column(name="title")
    private String title;
    
    @Column(name="truth")
    private boolean truth;
    
//    @Column(name="id_question")
//    private int id_question;
    
    @ManyToOne
    @JoinColumn(name = "id_question", unique = true)
    private Question question;

    public Answer() {
    }

    public Answer(int id) {
        this.id = id;
    }

    public Answer(int id_question, String code, String title, boolean truth) {
        this.code = code;
        this.title = title;
        this.truth = truth;
//        this.id_question = id_question;
        this.question = new Question(id_question);
    }

    public Answer(int id, int id_question, String code, String title, boolean truth) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.truth = truth;
//        this.id_question = id_question;
        this.question = new Question(id_question);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isTruth() {
        return truth;
    }

    public void setTruth(boolean truth) {
        this.truth = truth;
    }

//    public int getId_question() {
//        return id_question;
//    }
//
//    public void setId_question(int id_question) {
//        this.id_question = id_question;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
