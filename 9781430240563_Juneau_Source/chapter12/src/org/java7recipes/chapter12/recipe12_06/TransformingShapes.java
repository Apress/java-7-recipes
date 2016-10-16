package org.java7recipes.chapter12.recipe12_06;

import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * Transforming shapes.
 * 
 * @author cdea
 */
public class TransformingShapes extends JComponent {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // clear background
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        
        // turn on antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
        // save transform
        AffineTransform origTransform = g2d.getTransform();
        
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(3f));

        //Rectangle2D (original)
        Rectangle2D rectangle = new Rectangle2D.Float(50, 50, 50, 50);
        g2d.draw(rectangle); 
        
        // Shearing
        AffineTransform shear = new AffineTransform();
        
        // move to upper right
        shear.translate(rectangle.getX() + rectangle.getWidth() + 50, 0);

        shear.shear(-.5, 0);
        
        g2d.transform(shear);
        g2d.draw(rectangle);
        
        g2d.setTransform(origTransform);

        // rotate
        AffineTransform rotate = new AffineTransform();

        // move to bottom left
        rotate.translate(0, rectangle.getY() + rectangle.getHeight());

        rotate.rotate(Math.PI/4, rectangle.getCenterX() , rectangle.getCenterY());
        
        g2d.transform(rotate);
        g2d.draw(rectangle);

        g2d.setTransform(origTransform);
        
        // scale
        AffineTransform scale = new AffineTransform();

        // move to bottom right
        scale.translate(rectangle.getX() + 30, rectangle.getY());

        // scale
        scale.scale(1.5, 1.5);

        g2d.transform(scale);
        g2d.draw(rectangle);
        
    }

    public static void main(String[] args) {
        final TransformingShapes c = new TransformingShapes();
        c.setPreferredSize(new Dimension(317, 246));
        SimpleAppLauncher.launch("Chapter 12-6 Transforming Shapes", c);
    }
}