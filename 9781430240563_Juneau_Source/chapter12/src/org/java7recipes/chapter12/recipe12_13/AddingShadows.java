package org.java7recipes.chapter12.recipe12_13;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Transparency;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JComponent;

/**
 * Adding Shadows on Shapes
 * @author cdea
 */
public class AddingShadows extends JComponent {

    private void createDropShadow(Graphics g, Shape s) {
        
        int margin = 10;
        int padding = 5;
        int width = s.getBounds().width + padding + margin;
        int height = s.getBounds().height + padding + margin;
        Graphics2D g2d = (Graphics2D) g;
        GraphicsConfiguration gc = g2d.getDeviceConfiguration();
        BufferedImage srcImg = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        BufferedImage destImg = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        Graphics2D g2 = srcImg.createGraphics();
        
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, width, height);

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3.0f));
        g2.setPaint(Color.BLACK);
        g2.translate(-s.getBounds().x, -s.getBounds().y);
        int centerX = (width - s.getBounds().width ) / 2;
        int centerY = (height - s.getBounds().height ) / 2;
        g2.translate(centerX, centerY);

        g2.draw(s);
        float blurValue = 1.0f / 49.0f;
        float data[] = new float[49];
        for (int i=0; i<49; i++) {
            data[i] = blurValue;
        }
       
        Kernel kernel = new Kernel(7, 7, data);
        ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL,
                null);
        convolve.filter(srcImg, destImg);
        
        g2.dispose();

        g2d.drawImage(destImg, s.getBounds().y -padding, s.getBounds().x -padding, null);

    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // set color and thickness of stroke
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        // donut
        //g2d.setStroke(new BasicStroke(2));
        Ellipse2D bigCircle = new Ellipse2D.Float(50, 50, 100, 75);
        Ellipse2D smallCircle = new Ellipse2D.Float(80, 75, 35, 25);
        Area donut = new Area(bigCircle);
        Area donutHole = new Area(smallCircle);
        donut.subtract(donutHole);
        
        // draw drop shadow
        createDropShadow(g2d, donut);
        
        // draw orange donut
        g2d.setPaint(Color.ORANGE);
        g2d.fill(donut);

        // outline the donut
        g2d.setPaint(Color.BLACK);
        g2d.draw(donut);
    }

    public static void main(String[] args) {
        final AddingShadows c = new AddingShadows();
        c.setPreferredSize(new Dimension(334, 174));
        SimpleAppLauncher.launch("Chapter 12-13 Adding Shadows", c);
    }
}