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
public class EvaluationBO extends BO implements Serializable {
    
    private String code;
    private String title;
    private int duration;

    public EvaluationBO() {
    }

    public EvaluationBO(int id) {
        this.id = id;
    }

    public EvaluationBO(String code, String title, int duration) {
        this.code = code;
        this.title = title;
        this.duration = duration;
    }

    public EvaluationBO(int id, String code, String title, int duration) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
