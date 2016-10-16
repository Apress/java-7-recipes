package org.java7recipes.chapter14.recipe14_03;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Adding Component to GUI.
 * @author cdea
 */
public class AddingComponent2Gui extends JPanel {

    public AddingComponent2Gui(){
        // first name
        add(new JLabel("First Name"));
        add(new JTextField("Fred"));
        
        // last name
        add(new JLabel("Last Name"));
        add(new JTextField("Sanford"));

        // save button
        add(new JButton("Save"));
    }

    public static void main(String[] args) {
        final JPanel c = new AddingComponent2Gui();
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-3 Adding Components to GUI", c);
    }
}