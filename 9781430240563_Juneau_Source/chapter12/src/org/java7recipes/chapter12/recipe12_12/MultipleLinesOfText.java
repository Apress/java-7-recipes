package org.java7recipes.chapter12.recipe12_12;

import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import javax.swing.JComponent;

/**
 * Changing the text font.
 * 
 * @author cdea
 */
public class MultipleLinesOfText extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        // antialising
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Serif bold 
        Font serif = new Font("Serif", Font.BOLD, 22);
        g2d.setFont(serif);
        g2d.setPaint(Color.WHITE);
        String latinText = "parvulus enim natus est nobis filius datus est "
                + "nobis et factus est principatus super umerum eius et "
                + "vocabitur nomen eius Admirabilis consiliarius Deus "
                + "fortis Pater futuri saeculi Princeps pacis";
        
        AttributedString attrStr = new AttributedString(latinText);
        attrStr.addAttribute(TextAttribute.FONT, serif);
        AttributedCharacterIterator attrCharIter = attrStr.getIterator();
        FontRenderContext fontRenderCtx = g2d.getFontRenderContext();
        LineBreakMeasurer lineBreakMeasurer = 
                new LineBreakMeasurer(attrCharIter, fontRenderCtx);
        float wrapWidth = getParent().getWidth();
        float x = 0;
        float y = 0;
        while (lineBreakMeasurer.getPosition() < attrCharIter.getEndIndex()) {
            TextLayout textLayout = lineBreakMeasurer.nextLayout(wrapWidth);
            y += textLayout.getAscent();
            textLayout.draw(g2d, x, y);
            y += textLayout.getDescent() + textLayout.getLeading();
        }
    }

    public static void main(String[] args) {
        final MultipleLinesOfText c = new MultipleLinesOfText();
        c.setPreferredSize(new Dimension(330, 217));
        SimpleAppLauncher.launch("Chapter 12-12 Multiple Lines of Text", c);
    }
}