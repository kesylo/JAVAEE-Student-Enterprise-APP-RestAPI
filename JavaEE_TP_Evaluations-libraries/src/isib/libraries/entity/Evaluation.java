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

    public Evaluation() {
    }

    public Evaluation(int id) {
        this.id = id;
    }

    public Evaluation(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public Evaluation(int id, String code, String title) {
        this.id = id;
        this.code = code;
        this.title = title;
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
    
    
    
}
