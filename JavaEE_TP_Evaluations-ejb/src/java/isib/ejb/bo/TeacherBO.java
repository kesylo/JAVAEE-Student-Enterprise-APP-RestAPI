/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author LOIC
 */
public class TeacherBO extends PersonBO implements Serializable {

    public TeacherBO() {
        this.isStudent = false;
    }

    public TeacherBO(int id) {
        this.id = id;
        this.isStudent = false;
    }
    
    public TeacherBO(int id, UserBO user, String matricule, String firstname, String lastname, String address, String mobile_phone, String email, Date birthday) {
        this.id = id;
        this.matricule = matricule;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.birthday = birthday;
        this.user = user;
        this.isStudent = false;
    }

    public TeacherBO(UserBO user, String matricule, String firstname, String lastname, String address, String mobile_phone, String email, Date birthday) {
        this.matricule = matricule;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.birthday = birthday;
        this.user = user;
        this.isStudent = false;
    }
}
