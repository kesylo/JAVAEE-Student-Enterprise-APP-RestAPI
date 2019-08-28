/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
@Entity
@Table(name="teachers")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @Column(name="matricule", unique = true)
    private String matricule;
    
    @Column(name="firstname")
    private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="address")
    private String address;
    
    @Column(name="mobile_phone")
    private String mobile_phone;
    
    @Column(name="email")
    private String email;
    
    @Column(name="birthday")
    private Date birthday;
    
//    @Column(name="id_user")
//    private int id_user;
    
    @ManyToOne
    @JoinColumn(name = "id_user", unique = true)
    private User user;

    public Teacher() {
    }

    public Teacher(int id) {
        this.id = id;
    }
    
    public Teacher(int id, int id_user, String matricule, String firstname, String lastname, String address, String mobile_phone, String email, Date birthday) {
        this.id = id;
        this.matricule = matricule;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.birthday = birthday;
//        this.id_user = id_user;
        this.user = new User(id_user);
    }

    public Teacher(int id_user, String matricule, String firstname, String lastname, String address, String mobile_phone, String email, Date birthday) {
        this.matricule = matricule;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.birthday = birthday;
//        this.id_user = id_user;
        this.user = new User(id_user);
    }
    
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

//    public int getId_user() {
//        return id_user;
//    }
//
//    public void setId_user(int id_user) {
//        this.id_user = id_user;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
