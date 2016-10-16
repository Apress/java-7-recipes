/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java7recipes.chapter18.recipe18_10;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * Recipe 18-10
 * 
 * Servlet context listener example.
 * 
 * @author juneau
 */
@WebListener
public class StartupShutdownListener implements ServletContextListener	{

	/**
         * Invoked when the application is started up.
         * @param event 
         */
	public void contextInitialized(ServletContextEvent event) {
            System.out.println("Servlet startup...");
	    System.out.println(event.getServletContext().getServerInfo());
            System.out.println(System.currentTimeMillis());
	}

	/**
         * Invoked when the application is shut down.
         * @param event 
         */
	public void contextDestroyed(ServletContextEvent event) {
            System.out.println("Servlet shutdown...");
	    System.out.println(event.getServletContext().getServerInfo());
            System.out.println(System.currentTimeMillis());
        }
}



