/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java7recipes.chapter18.recipe18_11;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Recipe 18-11
 * 
 * Session attribute listener example.
 * 
 * @author juneau
 */
@WebListener
public class AttributeListener implements HttpSessionAttributeListener {

  public AttributeListener() {

  }

  public void attributeAdded(HttpSessionBindingEvent se) {

    HttpSession session = se.getSession();
    String id = session.getId();
    String name = se.getName();
    String value = (String) se.getValue();
    String source = se.getSource().getClass().getName();
    String message = new StringBuffer("Attribute bound to session in ")
        .append(source).append("\nThe attribute name: ").append(name)
        .append("\n").append("The attribute value:").append(value)
        .append("\n").append("The session ID: ").append(id).toString();
    System.out.println(message);
  }

  public void attributeRemoved(HttpSessionBindingEvent se) {

    HttpSession session = se.getSession();
    String id = session.getId();
    String name = se.getName();
    if (name == null)
      name = "Unknown";
    String value = (String) se.getValue();
    String source = se.getSource().getClass().getName();
    String message = new StringBuffer("Attribute unbound from session in ")
        .append(source).append("\nThe attribute name: ").append(name)
        .append("\n").append("The attribute value: ").append(value)
        .append("\n").append("The session ID: ").append(id).toString();
    System.out.println(message);
  }

  public void attributeReplaced(HttpSessionBindingEvent se) {

    String source = se.getSource().getClass().getName();
    String message = new StringBuffer("Attribute replaced in session  ")
        .append(source).toString();
    System.out.println(message);
  }
}