package org.java7recipes.chapter18.recipe18_12;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name="SessionServletTwo", urlPatterns={"/sessionServletTwo"}) 
public class SessionServletTwo extends HttpServlet {
  public void doGet (HttpServletRequest req, HttpServletResponse res)  
       throws ServletException, IOException {

 
   // Obtain the Session object
      
      HttpSession session = req.getSession(true);
 
   // Obtain session attribute
      String email = (String)              
      session.getAttribute ("session.email");  
      String sessionId = session.getId();
      
      res.setContentType("text/html"); 
      PrintWriter out = res.getWriter(); 
      out.println("<html>");  
      out.println("<head><title>Working with sessions</title></head>");
      out.println("<body>");
      out.println("<h1>Session Test</h1>");
      out.println ("Still remembers...tracking the session...<br/><br/> " +
                   "Your email address is: " + email + "<br/><br/>");
      out.println ("Your session id: " + sessionId);
      out.println("</body></html>");    
   }
}
