package org.java7recipes.chapter12.recipe12_03;

import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 * Draw a ellipse, rectangle, rounded rectangle.
 * 
 * @author cdea
 */
public class DrawShapes extends JComponent {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        // antialising
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // save current transform
        AffineTransform origTransform = g2d.getTransform();
        
        // paint black 
        g2d.setPaint(Color.BLACK);
        
        // 3 thickness
        g2d.setStroke(new BasicStroke(3));

        // Arc with open type
        Arc2D arc = new Arc2D.Float(50, // x coordinate 
                50,                     // y coordinate
                100,                    // bounds width 
                100,                    // bounds height
                45,                     // start angle in degrees
                270,                    // degrees plus start angle
                Arc2D.OPEN              // Open type arc
        );
        g2d.draw(arc);
        //drawArc(int x, int y, int width, int height, 
        //        int startAngle, int arcAngle);
        
        // Arc with chord type
        Arc2D arc2 = new Arc2D.Float(50, // x coordinate   
                50,                      // y coordinate
                100,                     // bounds width 
                100,                     // bounds height
                45,                      // start angle in degrees
                270,                     // degrees plus start angle
                Arc2D.CHORD              // Chord type arc
        );

        g2d.translate(arc.getBounds().width + 10, 0);
        g2d.draw(arc2);
        
        // Arc with Pie type (PacMan)
        Arc2D arc3 = new Arc2D.Float(50, // x coordinate  
                50,                      // y coordinate
                100,                     // bounds width  
                100,                     // bounds height
                45,                      // start angle in degrees
                270,                     // degrees plus start angle
                Arc2D.PIE                // pie type arc
        );
                
        g2d.translate(arc2.getBounds().width + 10, 0);
        g2d.draw(arc3);
        
        
        // reset transform
        g2d.setTransform(origTransform);
        g2d.translate(0, arc3.getHeight() + 10);
        
        
        //Ellipse2D
        Ellipse2D ellipse = new Ellipse2D.Float(50, 50, 100, 70);
        g2d.draw(ellipse); 
        // g.drawOval(50, 50, 100, 70);

        g2d.translate(0, ellipse.getBounds().getHeight() + 10);
        
        //Rectangle2D
        Rectangle2D rectangle = new Rectangle2D.Float(50, 50, 100, 70);
        g2d.draw(rectangle); 
        // g.drawRect(50, 50, 100, 70);
        
        g2d.translate(0, rectangle.getBounds().getHeight() + 10);
        
        //RoundRectangle2D
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(50, 50, 100, 70, 20, 20); 
        g2d.draw(roundRect); 
        // g.drawRoundRect(50, 50, 100, 70, 20, 20);
        
    }

    public static void main(String[] args) {
        final DrawShapes c = new DrawShapes();
        c.setPreferredSize(new Dimension(374, 415));
        SimpleAppLauncher.launch("Chapter 12-3 Draw Shapes", c);
    }
}