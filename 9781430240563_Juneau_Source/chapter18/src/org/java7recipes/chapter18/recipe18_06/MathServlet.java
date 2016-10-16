
package org.java7recipes.chapter18.recipe18_06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Recipe 18-6
 * 
 * Handling Requests and Responses
 * 
 * @author juneau
 */
public class MathServlet extends HttpServlet {
  

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws IOException, ServletException {

		res.setContentType("text/html");
                String numA = req.getParameter("numa");
                String numB = req.getParameter("numb");
                
                int solution = Integer.valueOf(numA) + Integer.valueOf(numB);
                
		PrintWriter out = res.getWriter();

		/* Display some response to the user */

		out.println("<html><head>");
		out.println("<title>Test Math Servlet</title>");
		out.println("\t<style>body { font-family: 'Lucida Grande', " +
			"'Lucida Sans Unicode';font-size: 13px; }</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<p>Solution: " +
			numA + " + " + numB + " = " + solution + "</p>");
                out.println("<br/><br/>");
                out.println("<a href='index.html'>Add Two More Numbers</a>");
		out.println("</body></html>");

		out.close();
	}
}  
