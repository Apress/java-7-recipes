
package org.java7recipes.chapter18.recipe18_18;

import javax.swing.*;

/**
 * Recipe 18-18
 * 
 * A simple mail sending form that utilizes external
 * libraries and includes Java Swing widgets.
 * 
 * @author juneau
 */
public class SwingMailApplet extends JApplet {

    public void init() {
        
        try{
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) { 
            System.err.println("createGUI didn't complete successfully");
        }
    }
    
    private void createGUI() {
        //Create and set up the content pane.
        SwingMailPanel mailPanel = new SwingMailPanel();
        mailPanel.setOpaque(true); 
        setContentPane(mailPanel);        
    }        
    
}
