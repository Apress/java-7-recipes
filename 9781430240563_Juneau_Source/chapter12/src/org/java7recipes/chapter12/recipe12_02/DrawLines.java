package org.java7recipes.chapter12.recipe12_02;

import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
 * Draws lines. Lines are colored Red, White and Blue.
 * @author cdea
 */
public class DrawLines extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.LIGHT_GRAY);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
      
        // Red line
        g2d.setPaint(Color.RED);
        // 5 visible, 5 invisible
        final float dash[] = {5, 5};
        // 10 thick, end lines with no cap, 
        // any joins make round, miter limit,
        // dash array, dash phase
        final BasicStroke dashed = new BasicStroke(10, BasicStroke.CAP_BUTT,  
                BasicStroke.JOIN_ROUND, 0, dash, 0);
        
        g2d.setStroke(dashed);
        g2d.drawLine(100, 10, 10, 110);
        
        // White line
        g2d.setPaint(Color.WHITE);
        g2d.setStroke(new BasicStroke(10.0f));
        g2d.draw(new Line2D.Float(150, 10, 10, 160));

        // Blue line
        g2d.setPaint(Color.BLUE);
        Stroke solidStroke = new BasicStroke(10, BasicStroke.CAP_ROUND, 
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(solidStroke);
        g2d.draw(new Line2D.Float(new Point2D.Float(200, 10), 
                new Point2D.Double(10, 210)));
    }

    public static void main(String[] args) {
        final DrawLines c = new DrawLines();
        c.setPreferredSize(new Dimension(272, 227));
        SimpleAppLauncher.launch("Chapter 12-2 Draw Lines", c);
    }
}