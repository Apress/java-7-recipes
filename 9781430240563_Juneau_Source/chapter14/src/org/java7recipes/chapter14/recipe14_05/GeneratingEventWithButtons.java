package org.java7recipes.chapter14.recipe14_05;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Generating Events With Buttons
 * @author cdea
 */
public class GeneratingEventWithButtons extends JPanel {

    public GeneratingEventWithButtons(){
        final JLabel status = new JLabel("Press the easy button to solve all your problems.");
        add(status);
        
        // save button
        JButton saveMe = new JButton("Easy");
        saveMe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                status.setText("You will recieve two tickets to the petting zoo...");
            }
        });
        add(saveMe);
    }

    public static void main(String[] args) {
        final JPanel c = new GeneratingEventWithButtons();
        c.setPreferredSize(new Dimension(384, 45));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-5 Generating Events With Buttons", c);
    }
}