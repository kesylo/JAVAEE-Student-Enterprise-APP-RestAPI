/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.libraries.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
@Entity
@Table(name="questions")
public class Question implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @Column(name="code", unique = true)
    private String code;
    
    @Column(name="title")
    private String title;
    
//    @Column(name="id_evaluation")
//    private int id_evaluation;
    
    @ManyToOne
    @JoinColumn(name = "id_evaluation", unique = true)
    private Evaluation evaluation;

    public Question() {
    }

    public Question(int id) {
        this.id = id;
    }

    public Question(int id_evaluation, String code, String title) {
        this.code = code;
        this.title = title;
//        this.id_evaluation = id_evaluation;
        this.evaluation = new Evaluation(id_evaluation);
    }

    public Question(int id, int id_evaluation, String code, String title) {
        this.id = id;
        this.code = code;
        this.title = title;
//        this.id_evaluation = id_evaluation;
        this.evaluation = new Evaluation(id_evaluation);
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

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

//    public int getId_evaluation() {
//        return id_evaluation;
//    }
//
//    public void setId_evaluation(int id_evaluation) {
//        this.id_evaluation = id_evaluation;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
