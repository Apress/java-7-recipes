
package org.java7recipes.chapter18.recipe18_04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Recipe 18-4
 * 
 * Registering Servlets without WEB-XML
 * 
 * @author juneau
 */
@WebServlet(name="SimpleServlet2", urlPatterns={"/SimpleServlet2"}) 
public class SimpleServlet2 extends HttpServlet {
  
        @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws IOException, ServletException {

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		/* Display some response to the user */

		out.println("<html><head>");
		out.println("<title>Simple Servlet 2</title>");
		out.println("\t<style>body { font-family: 'Lucida Grande', " +
			"'Lucida Sans Unicode';font-size: 13px; }</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<p>This is another simple servlet to show you how "
                        + "to deploy without listing the servlet within the "
                        + "web-xml configuration file.</p>");

		out.println("</body></html>");

		out.close();
	}
}  
