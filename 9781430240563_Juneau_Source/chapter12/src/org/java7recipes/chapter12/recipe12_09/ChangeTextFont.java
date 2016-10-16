package org.java7recipes.chapter12.recipe12_09;

import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 * Changing the text font.
 * 
 * @author cdea
 */
public class ChangeTextFont extends JComponent {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        // antialising
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        // Serif with drop shadow
        Font serif = new Font("Serif", Font.PLAIN, 30);
        g2d.setFont(serif);
        g2d.setPaint(new Color(50,50,50,150));
        g2d.drawString("Java 7 Recipes", 52, 52);
        // paint red 
        g2d.setPaint(Color.RED);
        g2d.drawString("Java 7 Recipes", 50, 50);
        
        
        // SanSerif
        g2d.setPaint(Color.BLUE);
        Font sanSerif = new Font("SanSerif", Font.PLAIN, 30);
        g2d.setFont(sanSerif);
        g2d.drawString("Java 7 Recipes", 50, 100);
        
        // Dialog
        g2d.setPaint(Color.GREEN);
        Font dialog = new Font("Dialog", Font.PLAIN, 30);
        g2d.setFont(dialog);
        g2d.drawString("Java 7 Recipes", 50, 150);
        
        // Monospaced
        g2d.setPaint(Color.BLACK);
        Font monospaced = new Font("Monospaced", Font.PLAIN, 30);
        g2d.setFont(monospaced);
        g2d.drawString("Java 7 Recipes", 50, 200);

    }

    public static void main(String[] args) {
        final ChangeTextFont c = new ChangeTextFont();
        c.setPreferredSize(new Dimension(330, 217));
        SimpleAppLauncher.launch("Chapter 12-9 Changing Text Font", c);
    }
}