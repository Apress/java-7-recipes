package org.java7recipes.chapter12.recipe12_04;

import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 * Draws lines. Lines are colored Red, White and Blue.
 * @author cdea
 */
public class FillColorShapes extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

        //Ellipse2D
        Ellipse2D ellipse = new Ellipse2D.Float(50, 50, 100, 70);
        g2d.draw(ellipse);
        g2d.setPaint(Color.RED);
        g2d.fill(ellipse);

        g2d.translate(0, ellipse.getBounds().getHeight() + 10);
        
        Stroke defaultStroke = g2d.getStroke();
        // Draw black line
        Line2D blackLine = new Line2D.Float(170, 30, 20, 140);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(10.0f));
        g2d.draw(blackLine);
        // set stroke back to normal
        g2d.setStroke(defaultStroke);
        
        //Rectangle2D
        Rectangle2D rectangle = new Rectangle2D.Float(50, 50, 100, 70);
        g2d.setPaint(Color.BLACK);
        g2d.draw(rectangle); 
        g2d.setPaint(new Color(255, 200, 0, 200));
        
        g2d.fill(rectangle);
        
        g2d.translate(0, rectangle.getBounds().getHeight() + 10);
        
        //RoundRectangle2D
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(50, 50, 100, 70, 20, 20); 
        g2d.setPaint(Color.BLACK);
        g2d.draw(roundRect);
        g2d.setPaint(Color.GREEN);
        g2d.fill(roundRect);

    }

    public static void main(String[] args) {
        final FillColorShapes c = new FillColorShapes();
        c.setPreferredSize(new Dimension(340, 320));
        SimpleAppLauncher.launch("Chapter 12-4 Filling Shapes with Colors", c);
    }
}