/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.war.listener;

import isib.war.bo.AppSetting;
import isib.war.bo.Connexion;
import java.util.Date;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;

/**
 *
 * @author LOIC
 */
public class SessionListener  implements HttpSessionListener {

    private static final Logger log4j = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        
        // -- Creation session value -- //
        httpSessionEvent.getSession().setAttribute("con", new Connexion(httpSessionEvent.getSession().getId()));
        // -- Creation application value -- //
        httpSessionEvent.getSession().getServletContext().setAttribute("appSettings", new AppSetting());
        
        log4j.info("sessionCreated: " + ((Connexion)httpSessionEvent.getSession().getAttribute("con")).getId_session() + ", date: " + new Date().toString());
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        
        log4j.info("sessionCreated: " + ((Connexion)httpSessionEvent.getSession().getAttribute("con")).getId_session() + ", date: " + new Date().toString());
    }
    
}
