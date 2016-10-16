package org.java7recipes.chapter14.recipe14_12;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.java7recipes.chapter14.CellConstraint;
import org.java7recipes.chapter14.CustomGridLayout;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * <p>
 * +------------------------+
 * | [label ] [ button ]    |
 * | [label ] [ button ]    |
 * | [label ] [ button ]    |
 * +------------------------+
 * </p>
 * 
 * Generating and Laying Out Icons.
 * @author cdea
 */
public final class GeneratingAndLayingIcons extends JPanel {

    public GeneratingAndLayingIcons() {
        JLabel label1 = new JLabel("Gold Spiral", createImageIcon("goldspiral.png"), JLabel.LEFT);
        label1.setHorizontalTextPosition(SwingConstants.RIGHT);
        JLabel label2 = new JLabel("Gold Circle", createImageIcon("goldcircle.png"), JLabel.LEFT);
        label2.setHorizontalTextPosition(SwingConstants.CENTER);
        JLabel label3 = new JLabel("Gold Star", createImageIcon("goldstar.png"), JLabel.LEFT);
        label3.setHorizontalTextPosition(SwingConstants.LEFT);
       
        JButton button1 = new JButton("Spiral", createImageIcon("spiral.png"));
        JButton button2 = new JButton("Cube", createImageIcon("cube.png"));
        button2.setHorizontalTextPosition(SwingConstants.CENTER);
        JButton button3 = new JButton("Pentagon", createImageIcon("pentagon.png"));
        button3.setHorizontalTextPosition(SwingConstants.LEFT);

        // create a layout 3x3 cell grid.
        // horizontal and vertical gaps between components are 5 pixels
        CustomGridLayout cglayout = new CustomGridLayout(10, 10, 3, 3);

        setLayout(cglayout);

        // add label1 cell 0,0
        addToPanel(label1, 0, 0, CellConstraint.RIGHT);

        // add label2 cell 1,0
        addToPanel(label2, 0,1, CellConstraint.RIGHT);

        // add label3 cell 2,0
        addToPanel(label3, 0,2, CellConstraint.RIGHT);

        // add button1 cell 0,1
        addToPanel(button1, 1, 0, CellConstraint.RIGHT);

        // add button2 cell 1,1
        addToPanel(button2, 1, 1, CellConstraint.RIGHT);

        // add button2 cell 2,1
        addToPanel(button3, 1, 2, CellConstraint.RIGHT);

    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            throw new RuntimeException("Unable to load " + path);
        }
    }

    private void addToPanel(Component comp, int colNum, int rowNum, int align) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum)
                .setAlign(align);
        add(comp, constr);
    }

    public static void main(String[] args) {
        final JPanel c = new GeneratingAndLayingIcons();
        c.setPreferredSize(new Dimension(388, 194));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-12 Generating and Laying Out Icons.", c);
    }
}