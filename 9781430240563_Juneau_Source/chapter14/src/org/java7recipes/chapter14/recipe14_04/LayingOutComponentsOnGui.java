package org.java7recipes.chapter14.recipe14_04;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static org.java7recipes.chapter14.recipe14_04.MyCellConstraint.LEFT;
import static org.java7recipes.chapter14.recipe14_04.MyCellConstraint.RIGHT;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * <pre>
 * +------------------------+
 * | [label ] [   field   ] |
 * | [label ] [   field   ] |
 * |             [ button ] |
 * +------------------------+
 * </pre>
 * Laying GUI Components.
 * @author cdea
 */
public class LayingOutComponentsOnGui extends JPanel {

    public LayingOutComponentsOnGui(){
        super();
        JLabel fNameLbl = new JLabel("First Name");
        JTextField fNameFld = new JTextField(15);
        JLabel lNameLbl = new JLabel("Last Name");
        JTextField lNameFld = new JTextField(15);
        JButton saveButt = new JButton("Save");

        // Create a 2x3 grid with 5 horizontal and vertical gaps 
        // between components.
        MyCustomGridLayout cglayout = new MyCustomGridLayout(5, 5, 2, 3);   
        
        setLayout(cglayout);
        
        // First name label
        addToPanel(fNameLbl, 0, 0, RIGHT);

        // Last name label
        addToPanel(lNameLbl, 0, 1, RIGHT);

        // First name field
        addToPanel(fNameFld, 1, 0, LEFT);
        
        // Last name field
        addToPanel(lNameFld, 1, 1, LEFT);

        // Save button
        addToPanel(saveButt, 1, 2, RIGHT);
    }
    
    private void addToPanel(Component comp, int colNum, int rowNum, int align) {
        MyCellConstraint constr = new MyCellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum)
                .setAlign(align);
        add(comp, constr);
    }

    public static void main(String[] args) {
        final JPanel c = new LayingOutComponentsOnGui();
        c.setPreferredSize(new Dimension(380, 118));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-4 Laying GUI Components", c);
    }
}