package org.java7recipes.chapter14.recipe14_20;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
 * Formatting components with HTML.
 * @author cdea
 */
public class FormattingGuiWithHtml extends JPanel {

    public FormattingGuiWithHtml() {
        JLabel label1 = new JLabel("<html><center><b>Label 1</b><br>"
                + "<font color=#7f7fdd>Bold</font>");
        JLabel label2 = new JLabel("<html><center><i>Label 2</i><br>"
                + "<font color=#7f7fdd>Italic</font>");
        JLabel label3 = new JLabel("<html><center><font size=+4>Label 3</font><br>"
                + "<font color=#7f7fdd>Larger</font>");

        JButton button1 = new JButton("<html><center><b><u>Button 1</u></b><br>"
                + "<font color=#7f7fdd>underline</font>");

        JButton button2 = new JButton("<html><font color=blue>Button 2</font><br>"
                + "<font color=#7f7fdd>Blue Left</font>");

        JButton button3 = new JButton("<html>Bu<sub>tt</sub>on 3<br>"
                + "<font color=#7f7fdd>Subscript</font>");

        // create a layout 3x3 cell grid.
        // horizontal and vertical gaps between components are 5 pixels
        CustomGridLayout cglayout = new CustomGridLayout(10, 10, 3, 3);

        setLayout(cglayout);

        // add label1 cell 0,0
        addToPanel(label1, 0, 0, CellConstraint.RIGHT);

        // add label2 cell 0,1
        addToPanel(label2, 0, 1, CellConstraint.RIGHT);

        // add label3 cell 0,2
        addToPanel(label3, 0, 2, CellConstraint.RIGHT);

        // add button1 cell 1,0
        addToPanel(button1, 1, 0, CellConstraint.CENTER);

        // add button2 cell 1,1
        addToPanel(button2, 1, 1, CellConstraint.CENTER);

        // add button2 cell 1,2
        addToPanel(button3, 1, 2, CellConstraint.CENTER);

    }

    private void addToPanel(Component comp, int colNum, int rowNum, int align) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum)
                .setAlign(align);
        add(comp, constr);
    }

    public static void main(String[] args) {
        final JPanel c = new FormattingGuiWithHtml();
        c.setPreferredSize(new Dimension(377, 194));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-20 Formatting GUI with Html.", c);
    }
}