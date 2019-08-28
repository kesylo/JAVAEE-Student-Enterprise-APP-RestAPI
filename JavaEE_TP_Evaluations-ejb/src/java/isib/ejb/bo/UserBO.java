/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
public class UserBO extends BO implements Serializable {
    
    private String login;
    private String password;
    private boolean enabled;
//    private Set<StudentBO> students = new HashSet<StudentBO>();
//    private Set<TeacherBO> teachers = new HashSet<TeacherBO>();

    public UserBO() {
    }

    public UserBO(int id) {
        this.id = id;
    }

    public UserBO(String login, String password, boolean enabled) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public UserBO(int id, String login, String password, boolean enabled) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
//    public Set<StudentBO> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<StudentBO> students) {
//        this.students = students;
//    }
//
//    public Set<TeacherBO> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(Set<TeacherBO> teachers) {
//        this.teachers = teachers;
//    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
