package org.java7recipes.chapter14.recipe14_24;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.LayerUI;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * <p>
 * +------------------------+
 * | [label ] [   field   ] |
 * |             [ button ] |
 * +------------------------+
 * </p>
 * 
 * Using JLayer.
 * @author cdea
 */
public class UsingJXLayer extends JPanel {

    public UsingJXLayer() {

        setLayout(new BorderLayout(10, 20));

        // create input area
        JPanel inputArea = new JPanel();

        JLabel emailLbl = new JLabel("Email");

        // target email field
        final JTextField emailFld = new JTextField(20);

        Image error = null;
        Image warning= null;
        try {
            error = ImageIO.read(this.getClass().getResource("error.png"));
            warning = ImageIO.read(this.getClass().getResource("warning.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        // email LayerUI
        LayerUI<JTextField> layerUI = new EmailValidationLayerUI(error, warning);

        // JLayer applying layerUI to email field 
        JLayer<JTextField> layeredEmail = new JLayer<>(emailFld, layerUI);

        inputArea.add(emailLbl);
        inputArea.add(layeredEmail);

        add(inputArea, BorderLayout.NORTH);

        JComponent buttonArea = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton saveButt = new JButton("Save");

        buttonArea.add(saveButt);

        add(buttonArea, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        final JPanel c = new UsingJXLayer();
        c.setPreferredSize(new Dimension(402, 118));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-24 Using JLayer.", c);
    }
}

class EmailValidationLayerUI extends LayerUI<JTextField> {

    Image errorImg;
    Image warningImg;
    
    public EmailValidationLayerUI(Image error, Image warning) {
        this.errorImg = error;
        this.warningImg = warning;
    }
    @Override
    public void paint(Graphics g, JComponent comp) {
        super.paint(g, comp);

        JLayer jlayer = (JLayer) comp;
        JTextField emailFld = (JTextField) jlayer.getView();
        String text = emailFld.getText();
        String regEx = ".+@.+\\.[A-Za-z]+";

        int x = comp.getWidth() - 12;
        int y = (comp.getHeight() - 8) / 2;
        
        if (text.length() > 0 && !(text.matches(regEx))) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.drawImage(errorImg, x, y, comp);
            g2.dispose();
        } else if (text.length() > 0 && text.substring(text.lastIndexOf("."), text.length()).length() > 4) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.drawImage(warningImg, x, y, comp);
            g2.dispose();
        }
    }

}