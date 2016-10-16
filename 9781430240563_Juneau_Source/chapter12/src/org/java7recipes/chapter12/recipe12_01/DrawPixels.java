/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java7recipes.chapter12.recipe12_01;

import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author cdea
 */
public class DrawPixels extends JComponent{
    Random rnd = new Random();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        
        for (int i=0; i<3000; i++) {
            int red = rnd.nextInt(256);
            int green = rnd.nextInt(256);
            int blue = rnd.nextInt(256);
            g2d.setColor(new Color(red, green, blue));
        
            int rndX = rnd.nextInt(getSize().width);
            int rndY = rnd.nextInt(getSize().height);

            g2d.drawLine(rndX, rndY, rndX, rndY);
        }
    }

    public static void main(String[] args) {
        final DrawPixels c = new DrawPixels();
        c.setPreferredSize(new Dimension(359, 320));
        SimpleAppLauncher.launch("Chapter 12-1 Creating Points - DrawPixels", c);
    }
}