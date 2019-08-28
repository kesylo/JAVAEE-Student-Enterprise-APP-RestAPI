/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.war.filter;

import isib.war.bo.Connexion;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author LOIC
 */
public class FilterAuthentication implements Filter {

    private static final Logger log4j = Logger.getLogger(FilterAuthentication.class);

    private Connexion con;
    private final String[] servlets = new String[] {"Teacher", "Student", "User", "Answer", "Question", "Evaluation"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
            
            HttpSession session = httpRequest.getSession(true);
            
            if (session.getAttribute("con") != null) {
                this.con = (Connexion)session.getAttribute("con");
//                if(this.con.getPerson().isIsStudent())
            }
            
            String uri = httpRequest.getRequestURI();
            boolean allow;
            
            for(String val : servlets)
            
            if (!uri.contains("resources")) {
                
            }
        }
        
        chain.doFilter(request, response);

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        try {
//            this.con = Tools.getConnexionSession(req);
//
//            if (this.con == null || this.con.getPerson().getId() == 0) {
//                Tools.redirectToPage(req, res, "views/authentication/index.jsp");
//            } else {
//                chain.doFilter(request, response);
//            }
//        } catch (IOException | ServletException ex) {
//            log4j.error(ex);
//            Tools.redirectToPage(req, res, "views/authentication/index.jsp");
//        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log4j.info("FilterAuthentication: init");
    }

    @Override
    public void destroy() {
        log4j.info("FilterAuthentication: destroy");
    }

}
