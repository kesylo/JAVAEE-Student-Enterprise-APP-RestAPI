/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.apiRest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @Column(name="code", unique = true)
    private String code;
    
    @Column(name="title")
    private String title;
    
    @Column(name="duration")
    private int duration;
    
    @OneToMany(mappedBy = "evaluation", fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<Question>();

    public Evaluation() {
    }

    public Evaluation(int id) {
        this.id = id;
    }

    public Evaluation(String code, String title, int duration) {
        this.code = code;
        this.title = title;
        this.duration = duration;
    }

    public Evaluation(int id, String code, String title, int duration) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
