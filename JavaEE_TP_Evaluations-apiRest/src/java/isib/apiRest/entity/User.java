/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.apiRest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @Column(name="login", unique = true)
    private String login;
    
    @Column(name="password")
    private String password;
    
    @Column(name="enabled")
    private boolean enabled;
    
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Student> students = new HashSet<Student>();
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Teacher> teachers = new HashSet<Teacher>();
            
//    @OneToOne
//    @JoinColumn(name = "id_student")
//    private Student student;
//    
//    @ManyToOne
//    @JoinColumn(name = "id_teacher", unique = true)
//    private Teacher teacher;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String login, String password, boolean enabled) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public User(int id, String login, String password, boolean enabled) {
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

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
    
//    public Set<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<Student> students) {
//        this.students = students;
//    }
//
//    public Set<Teacher> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(Set<Teacher> teachers) {
//        this.teachers = teachers;
//    }    

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
