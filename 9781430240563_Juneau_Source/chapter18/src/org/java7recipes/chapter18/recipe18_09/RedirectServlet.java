/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java7recipes.chapter18.recipe18_09;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Recipe 18-9
 * 
 * Redirect Servlet simply redirects a web page to another URL.
 * 
 * @author juneau
 */
@WebServlet(name="RedirectServlet", urlPatterns={"/redirect"}) 
public class RedirectServlet extends HttpServlet {
  
        @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws IOException, ServletException {
                String site = "http://www.java.net";
                
		res.sendRedirect(site);
	}
}  

