
package org.java7recipes.chapter18.recipe18_14;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Recipe 18-14
 * 
 * Creating an Applet
 * 
 * @author juneau
 */

public class FirstApplet extends Applet implements ActionListener {

    Label buttonLabel;
    Button messageButton;

    public void init() {

        // Construct the button
        messageButton = new Button("Button");

        buttonLabel = new Label("Press Button");


        // Set the layout
        this.setLayout(new FlowLayout());

        // Add button to layout
        this.add(messageButton);
        // Add label to layout
        this.add(buttonLabel);

        // Set the action event equal to this class since it 
        // implements ActionListener
        messageButton.addActionListener(this);
        this.setVisible(true);
    }

    /**
     * Action method for this applet.  This method will be called when this
     * class is set as an action listener.
     * 
     * @param e 
     */
    public void actionPerformed(ActionEvent e) {
        if (this.buttonLabel.getText().equals("Press Button")) {
            this.buttonLabel.setText("Hello Java 7!");
        } else {
            this.buttonLabel.setText("Press Button");
        }
        repaint();
    }
}
