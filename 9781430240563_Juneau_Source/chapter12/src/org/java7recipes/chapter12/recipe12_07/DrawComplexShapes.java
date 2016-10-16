package org.java7recipes.chapter12.recipe12_07;

import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JComponent;

/**
 * Draws Complex shapes.
 * @author cdea
 */
public class DrawComplexShapes extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // set color and thickness of stroke
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

        //CubicCurve2D
        CubicCurve2D cubicCurve = new CubicCurve2D.Float(
                50, 75,            // start pt (x1,y1)
                50+30, 75-100,     // control pt1
                50+60, 75+100,     // control pt2 
                50+90, 75          // end pt (x2,y2)
        );
        
        g2d.draw(cubicCurve); 

        // move below previous shape
        g2d.translate(0, cubicCurve.getBounds().y + 50);
        
        //Path2D (IceCream shape)
        Path2D path = new Path2D.Float();
        path.moveTo(50, 150);           
        path.quadTo(100, 50, 150, 150);
        path.lineTo(50, 150);
        path.lineTo(100, 150 + 125);
        path.lineTo(150, 150);
        path.closePath();
        
        g2d.draw(path);

        // move below previous shape
        g2d.translate(0, path.getBounds().height + 50);
        
        //QuadCurve2D
        QuadCurve2D quadCurve = new QuadCurve2D.Float(50, 50, 
                125, 150, 
                150, 50
        );
        
        g2d.draw(quadCurve);

        // move below previous shape
        g2d.translate(0, quadCurve.getBounds().y + 50);
        
        // donut
        g2d.setStroke(new BasicStroke(1));
        Ellipse2D bigCircle = new Ellipse2D.Float(50, 50, 100, 75);
        Ellipse2D smallCircle = new Ellipse2D.Float(80, 75, 35, 25);
        Area donut = new Area(bigCircle);
        Area donutHole = new Area(smallCircle);
        donut.subtract(donutHole);

        // drop shadow
        GradientPaint gradient2 = new GradientPaint(150 +1, 50+75 +1, 
                new Color(255, 255, 255, 200), 
                55, 55, 
                new Color(0, 0, 0, 200)
        );
        // gradient fill
        g2d.setPaint(gradient2);
        g2d.fill(donut);
        g2d.draw(donut);
        
        // draw orange donut
        g2d.translate(-3, -3);
        g2d.setPaint(Color.ORANGE);
        g2d.fill(donut);

        // outline the donut
        g2d.setPaint(Color.BLACK);
        g2d.draw(donut);        
    }

    public static void main(String[] args) {
        final DrawComplexShapes c = new DrawComplexShapes();
        c.setPreferredSize(new Dimension(409, 726));
        SimpleAppLauncher.launch("Chapter 12-7 Draw Complex Shapes", c);
    }
}