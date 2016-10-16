package org.java7recipes.chapter18.recipe18_08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

/**
 * Recipe 18-8
 * 
 * This filter obtains the IP address of the remote host and logs it.
 * 
 * @author juneau
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {

    private FilterConfig filterConf = null;

    public void init(FilterConfig filterConf) {
        this.filterConf = filterConf;
    }

    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String userAddy = request.getRemoteHost();
        
        filterConf.getServletContext().log("Vistor User IP: " + userAddy);

        chain.doFilter(request, response);

  }

  public void destroy() {
    }
}