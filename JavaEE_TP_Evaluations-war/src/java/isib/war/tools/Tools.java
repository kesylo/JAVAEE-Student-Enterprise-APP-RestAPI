/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.war.tools;

import isib.ejb.entity.Student;
import isib.ejb.entity.Teacher;
import isib.war.bo.Connexion;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author LOIC
 */
public final class Tools {

    private static final Logger log4j = Logger.getLogger(Tools.class);

    public static String htmlBoutonActionTable(int id) {
        return "<div class=\"btn-group btn-group-sm\" role=\"group\">"
                + "<button id=\"btn_group_action_" + id + "\" type=\"button\" class=\"btn btn-sm btn-default dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
                + "Action"
                + "</button>"
                + "<div class=\"dropdown-menu\" aria-labelledby=\"btn_group_action_" + id + "\">"
                + "<a class=\"dropdown-item app-modify\" href=\"Javascript:;\" id=\"table_donnee_modifier_id_" + id + "\" title=\"{Lang.Update}\" onClick=\"table_donnee_modifier('" + id + "')\">"
                + "<i class=\"fa fa-retweet\"></i> Update"
                + "</a>"
                + "<a class=\"dropdown-item app-remove\" href=\"Javascript:;\" id=\"table_donnee_supprimer_id_" + id + "\" title=\"{Lang.Delete}\" onClick=\"table_donnee_supprimer('" + id + "')\">"
                + "<i class=\"fa fa-trash\"></i> Delete"
                + "</a>"
                + "</div>"
                + "</div>";
    }

    public static String htmlBoutonActionTable(int id, String function, String title) {
        return "<div class=\"btn-group btn-group-sm\" role=\"group\">"
                + "<button id=\"btn_group_action_" + id + "\" type=\"button\" class=\"btn btn-sm btn-default dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
                + "Action"
                + "</button>"
                + "<div class=\"dropdown-menu\" aria-labelledby=\"btn_group_action_" + id + "\">"
                + "<a class=\"dropdown-item app-modify\" href=\"Javascript:;\" id=\"table_donnee_modifier_id_" + id + "\" title=\"{Lang.Update}\" onClick=\"table_donnee_modifier('" + id + "')\">"
                + "<i class=\"fa fa-retweet\"></i> Update"
                + "</a>"
                + "<a class=\"dropdown-item app-remove\" href=\"Javascript:;\" id=\"table_donnee_supprimer_id_" + id + "\" title=\"{Lang.Delete}\" onClick=\"table_donnee_supprimer('" + id + "')\">"
                + "<i class=\"fa fa-trash\"></i> Delete"
                + "</a>"
                + "<a class=\"dropdown-item\" href=\"Javascript:;\" onClick=\"" + function + "('" + id + "')\">"
                + "<i class=\"fa fa-check\"></i> " + title
                + "</a>"
                + "</div>"
                + "</div>";
    }

    public static String htmlCheckboxTable(int id, String name) {
        return "<input type=\"checkbox\" class=\"flat-iCheck\" name=\"" + name + "\" value=\"" + name + "_" + id + "\">";
    }

    public static void redirectToPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        RequestDispatcher requestDispacher = request.getRequestDispatcher(url);
        requestDispacher.forward(request, response);

    }

    public static void checkIsConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connexion con = getConnexionSession(request);
        
        if (!con.isAuthentificated()) {
            Tools.redirectToPage(request, response, "/Authentication");
        }

    }

    public static Connexion getConnexionSession(HttpServletRequest request) {

        return (Connexion) request.getSession().getAttribute("con");

    }

    public static String htmlBoutonPerson(int id, Object person, boolean isStudent) {
        return
            ((isStudent) ? "(<b>S</b>) " + ((Student)person).getFirstname() + " " + ((Student)person).getLastname()
                         : "(<b>T</b>) " + ((Teacher)person).getFirstname() + " " + ((Teacher)person).getLastname());
    }

    public static Date convertToDate(String value) {
        
        return
            new Date(
                (Integer.parseInt(value.split("/")[2]) - 1900),
                Integer.parseInt(value.split("/")[1]) - 1,
                Integer.parseInt(value.split("/")[0])
            );

    }

    public static String convertToString(Date value) {
        
        return
            (value.getDate() < 10 ? "0" + value.getDate() 
                                  : value.getDate()) + "/" + ((value.getMonth() + 1 )< 10 ? "0" + (value.getMonth() + 1 )
                                                                                          : (value.getMonth() + 1 )) + "/" + (value.getYear() + 1900);

    }

    public static String htmlBoutonDisplayList(int id, int length, String nameObjet) {
        
        return 
            "<button id=\"btn_displayList_" + id + "\" type=\"button\" class=\"btn btn-sm btn-block btn-default\" onClick=\"displayList(" + id + ")\">" +
                length + " " + nameObjet +
            "</button>";
        
    }
    
    public static int getAge(Date birthday){
        
        return 
            Period.between(
                birthday.toInstant().atZone(
                    ZoneId.systemDefault()
                ).toLocalDate(), 
                LocalDate.now()
            ).getYears();
            
    }
}
