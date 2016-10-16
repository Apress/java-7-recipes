package org.java7recipes.chapter12.recipe12_05;

import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 * Draw a ellipse, rectangle, rounded rectangle.
 * 
 * @author cdea
 */
public class Gradients extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(new Color(255, 255, 255, 200));
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Ellipse2D
        Ellipse2D ellipse = new Ellipse2D.Float(50, 50, 100, 70);
        
        float[] dists = { .3f, 1.0f};
        Color[] colors = {Color.RED, Color.BLACK};
        RadialGradientPaint gradient1 = new RadialGradientPaint(50, 50, 100, dists, colors);
        g2d.setPaint(gradient1);
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
        float[] dists2 = { .1f, 1.0f};
        Color[] colors2 = {new Color(255, 200, 0, 200), new Color(0, 0, 0, 200)};
        LinearGradientPaint gradient2 = new LinearGradientPaint(100, 50, 100, 150, dists2, colors2);
        
        g2d.setPaint(gradient2);
        g2d.fill(rectangle); 
        
        g2d.translate(0, rectangle.getBounds().getHeight() + 10);
        
        //RoundRectangle2D
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(50, 50, 100, 70, 20, 20);
        GradientPaint gradient3 = new GradientPaint(50, 50, Color.GREEN, 70,70, Color.BLACK, true);
        g2d.setPaint(gradient3);
        g2d.fill(roundRect); 
    }

    public static void main(String[] args) {
        final Gradients c = new Gradients();
        c.setPreferredSize(new Dimension(287, 320));
        SimpleAppLauncher.launch("Chapter 12-5 Gradients", c);
    }
}