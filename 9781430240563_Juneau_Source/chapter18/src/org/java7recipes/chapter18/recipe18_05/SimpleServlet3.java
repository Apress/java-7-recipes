
package org.java7recipes.chapter18.recipe18_05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Recipe 18-5
 * 
 * Setting Initialization Parameters
 * 
 * @author juneau
 */
@WebServlet(name="SimpleServlet3", urlPatterns={"/SimpleServlet3"},
initParams={ @WebInitParam(name="name", value="Duke") }) 
public class SimpleServlet3 extends HttpServlet {
  
        @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws IOException, ServletException {

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		/* Display some response to the user */

		out.println("<html><head>");
		out.println("<title>Simple Servlet 3</title>");
		out.println("\t<style>body { font-family: 'Lucida Grande', " +
			"'Lucida Sans Unicode';font-size: 13px; }</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<p>This is a simple servlet!  Hello " + getServletConfig().getInitParameter("name") + "</p>");

		out.println("</body></html>");

		out.close();
	}
}  
