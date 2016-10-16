package org.java7recipes.chapter12.recipe12_10;

import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.JComponent;

/**
 * Adding Attributes to Text.
 * 
 * @author cdea
 */
public class AddingAttributesToText extends JComponent {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        // antialising
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AttributedString attrStr = new AttributedString("Java7Recipes");
        
        // Serif, plain 'Java'
        Font serif = new Font(Font.SERIF, Font.PLAIN, 50);
        attrStr.addAttribute(TextAttribute.FONT, serif, 0, 4);
        
        // Underline 'Java'
        attrStr.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, 4);
        
        // Background black for 'Java'
        attrStr.addAttribute(TextAttribute.BACKGROUND, Color.BLACK, 0, 4);
        
        // SanSerif, Bold, Italic '7'
        Font sanSerif = new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 50);
        attrStr.addAttribute(TextAttribute.FONT, sanSerif, 4, 5);

        // Make a rainbow colors on 'Java7Re'
        // Roy G. Biv (red, orange, yellow, green, blue, indigo, violet)
        Paint[] rainbow = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, 
            Color.GREEN, 
            Color.BLUE, new Color(75, 0, 130), new Color(127, 0, 255)
        };
        
        for (int i=0; i<rainbow.length; i++) {
            attrStr.addAttribute(TextAttribute.FOREGROUND, rainbow[i], i, i+1);    
        }
        
        // MonoSpaced, Bold 'Recipes'
        Font monoSpaced = new Font(Font.MONOSPACED, Font.BOLD, 50);
        attrStr.addAttribute(TextAttribute.FONT, monoSpaced, 5, 12);
        
        // Strike through 'Recipes'
        attrStr.addAttribute(TextAttribute.STRIKETHROUGH, Boolean.TRUE, 5, 12);
        g2d.drawString(attrStr.getIterator(), 50, 100);
       
    }

    public static void main(String[] args) {
        final AddingAttributesToText c = new AddingAttributesToText();
        c.setPreferredSize(new Dimension(410, 148));
        SimpleAppLauncher.launch("Chapter 12-10 Adding Attributes To Text", c);
    }
}