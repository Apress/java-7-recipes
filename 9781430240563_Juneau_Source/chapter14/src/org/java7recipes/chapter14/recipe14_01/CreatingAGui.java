package org.java7recipes.chapter14.recipe14_01;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Creating a GUI.
 * @author cdea
 */
public class CreatingAGui extends JComponent {

    /**
     * @param title the Chapter and recipe.
     * @param canvas the drawing surface.
     */
    protected static void displayGUI(final String title, final JComponent component) {

        // create window with title
        final JFrame frame = new JFrame(title);

        // set window's close button to exit application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // place component in the center using BorderLayout
        frame.getContentPane().add(component, BorderLayout.CENTER);

        // size window based on layout
        frame.pack();

        // center window
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        int scrnWidth = frame.getSize().width;
        int scrnHeight = frame.getSize().height;
        int x = (scrnSize.width - scrnWidth) / 2;
        int y = (scrnSize.height - scrnHeight) / 2;

        // Move the window
        frame.setLocation(x, y);

        // display 
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final CreatingAGui c = new CreatingAGui();
        c.setPreferredSize(new Dimension(290, 227));
        
        // Queueing GUI work to be run using the EDT.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayGUI("Chapter 14-1 Creating a GUI", c);
            }
        });
    }
}