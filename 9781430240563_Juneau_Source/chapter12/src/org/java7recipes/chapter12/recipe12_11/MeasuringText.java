package org.java7recipes.chapter12.recipe12_11;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.java7recipes.chapter12.AppSetup;

/**
 * Measuring Text.
 * 
 * @author cdea
 */
public class MeasuringText extends JComponent implements AppSetup {
    public static final String LEFT = "left";
    public static final String CENTER = "center";
    public static final String RIGHT = "right";
    
    private String justifyText = "left";
    
    public String getJustification() {
        return justifyText;
    }
    
    public void setJustification(String justify) {
        justifyText = justify.toLowerCase();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        // antialising
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);


        // SanSerif
        g2d.setPaint(Color.BLUE);
        Font serif = new Font(Font.SERIF, Font.PLAIN, 30);
        g2d.setFont(serif);
        
        String sentence1 = "The quick brown fox jumped";
        String sentence2 = "over the lazy dog.";

        FontRenderContext fontRenderCtx = g2d.getFontRenderContext();
        Rectangle2D bounds1 = serif.getStringBounds(sentence1, fontRenderCtx);
        Rectangle2D bounds2 = serif.getStringBounds(sentence2, fontRenderCtx);

        int y = 50;
        int x = 0;
        FontMetrics fm = g2d.getFontMetrics();
        int spaceRow = fm.getDescent() + fm.getLeading() + fm.getAscent();
        
        String justify = getJustification();
        switch(justify) {
            case CENTER:
                x = (getParent().getWidth() - (int)bounds1.getWidth())/2;
                g2d.drawString(sentence1, x, y);
                x = (getParent().getWidth() - (int)bounds2.getWidth())/2;
                g2d.drawString(sentence2, x, y + spaceRow);
                break;
            case RIGHT:
                x = (getParent().getWidth() - (int) bounds1.getWidth());
                g2d.drawString(sentence1, x, y);
                x = (getParent().getWidth() - (int) bounds2.getWidth());
                g2d.drawString(sentence2, x, y + spaceRow);
                break;
            case LEFT:
            default:
                g2d.drawString(sentence1, x, y);
                g2d.drawString(sentence2, x, y + spaceRow);
                break;
        }
    }

    @Override
    public void apply(JFrame frame) {

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Justification");
        menuBar.add(menu);

        JMenuItem leftMenuItem = new JMenuItem("Left");
        menu.add(leftMenuItem);
        leftMenuItem.addActionListener(
                new JustificationAction(leftMenuItem.getText(), this));
        
        JMenuItem centerMenuItem = new JMenuItem("Center");
        menu.add(centerMenuItem);
        centerMenuItem.addActionListener(
                new JustificationAction(centerMenuItem.getText(), this));
        
        JMenuItem rightMenuItem = new JMenuItem("Right");
        menu.add(rightMenuItem);
        rightMenuItem.addActionListener(
                new JustificationAction(rightMenuItem.getText(), this));

        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        final MeasuringText c = new MeasuringText();
        c.setPreferredSize(new Dimension(391, 114));
        SimpleAppLauncher.launch("Chapter 12-11 Measuring Text", c);
    }
}

/**
 * Action to set the justification.
 */
class JustificationAction extends AbstractAction {
    private MeasuringText component;
    
    public JustificationAction(String command, MeasuringText component) {
        super(command);
        this.component = component;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand().toLowerCase();
        component.setJustification(command);
        component.repaint();
    }
}