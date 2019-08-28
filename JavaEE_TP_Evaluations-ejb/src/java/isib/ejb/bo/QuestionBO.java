/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.bo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
public class QuestionBO extends BO implements Serializable {
    
    private String code;
    private String title;
    private int marks;
    private EvaluationBO evaluation;

    public QuestionBO() {
    }

    public QuestionBO(int id) {
        this.id = id;
    }

    public QuestionBO(int id_evaluation, String code, String title, int marks) {
        this.code = code;
        this.title = title;
        this.marks = marks;
        this.evaluation = new EvaluationBO(id_evaluation);
    }

    public QuestionBO(int id, int id_evaluation, String code, String title, int marks) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.marks = marks;
        this.evaluation = new EvaluationBO(id_evaluation);
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public EvaluationBO getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationBO evaluation) {
        this.evaluation = evaluation;
    }
}
