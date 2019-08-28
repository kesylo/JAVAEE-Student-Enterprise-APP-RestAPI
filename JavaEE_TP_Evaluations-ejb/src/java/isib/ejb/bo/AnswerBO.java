/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.bo;

import java.io.Serializable;

/**
 *
 * @author LOIC
 */
public class AnswerBO extends BO implements Serializable {
    
    private String code;
    private String title;
    private boolean truth;
    private QuestionBO question;

    public AnswerBO() {
    }

    public AnswerBO(int id) {
        this.id = id;
    }

    public AnswerBO(int id_question, String code, String title) {
        this.code = code;
        this.title = title;
        this.question = new QuestionBO(id_question);
    }

    public AnswerBO(int id, int id_question, String code, String title) {
        this.id = id;
        this.code = code;
        this.title = title;
//        this.id_question = id_question;
        this.question = new QuestionBO(id_question);
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

    public QuestionBO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBO question) {
        this.question = question;
    }

    public boolean isTruth() {
        return truth;
    }

    public void setTruth(boolean truth) {
        this.truth = truth;
    }
}
