
package org.java7recipes.chapter18.recipe18_02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Recipe 18-2
 * 
 * Simple Servlet that accepts two input parameters as Strings, 
 * converts them to int values, and adds them together.
 * 
 * @author juneau
 */
public class SimpleServlet extends HttpServlet {
  

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws IOException, ServletException {

		res.setContentType("text/html");

                
		PrintWriter out = res.getWriter();

		/* Display some response to the user */

		out.println("<html><head>");
		out.println("<title>First Servlet</title>");
		out.println("\t<style>body { font-family: 'Lucida Grande', " +
			"'Lucida Sans Unicode';font-size: 13px; }</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<p>This is a simple servlet!</p>");

		out.println("</body></html>");

		out.close();
	}
}  
