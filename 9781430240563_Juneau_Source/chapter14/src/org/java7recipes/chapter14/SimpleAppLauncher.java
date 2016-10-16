package org.java7recipes.chapter14;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * SimpleAppLauncher will create a window and display a component and
 * abide by the event dispatch thread rules.
 * 
 * @author cdea
 */
public class SimpleAppLauncher {
    /**
     * @param title the Chapter and recipe.
     * @param canvas the drawing surface.
     */
    protected static void displayGUI(final String title, final JComponent component) {
        
        // create window with title
        final JFrame frame = new JFrame(title);
        if (component instanceof AppSetup) {
            AppSetup ms = (AppSetup) component;
            ms.apply(frame);
        }
        
        // set window's close button to exit application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        component.addComponentListener(new ComponentAdapter() {  
            // This method is called after the component's size changes
            @Override
            public void componentResized(ComponentEvent evt) {
                Component c = (Component)evt.getSource();
                
                // Get new size
                Dimension newSize = c.getSize();
                System.out.println("component size w,h = " + newSize.getWidth() + ", " + newSize.getHeight());
            }
        });
        
        // place component in the center using BorderLayout
        frame.getContentPane().add(component, BorderLayout.CENTER);
        frame.setMinimumSize(component.getMinimumSize());
        
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
    
    public static void launch(final String title, final JComponent component) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayGUI(title, component);
            }
        });// invokeLater()
    }// launch()
} // SimpleAppLauncher
