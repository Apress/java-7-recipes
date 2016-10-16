package org.java7recipes.chapter14.recipe14_04;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * +------------------------+
 * | [label ] [   field   ] |
 * | [label ] [   field   ] |
 * |             [ button ] |
 * +------------------------+
 * 
 * Laying Out Components On GUI.
 * @author cdea
 */
public class LayingOutComponentsOnGuiGroupLayout extends JPanel {

    public LayingOutComponentsOnGuiGroupLayout(){
        JLabel fNameLbl = new JLabel("First Name");
        JTextField fNameFld = new JTextField();
        JLabel lNameLbl = new JLabel("Last Name");
        JTextField lNameFld = new JTextField();
        JButton saveButt = new JButton("Save");

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        
        groupLayout.setAutoCreateGaps(true);
        
        // Create gaps around the componet touching the parent contain (JPanel)
        groupLayout.setAutoCreateContainerGaps(true);
        
        // create a sequence of column groups.
        GroupLayout.SequentialGroup columnGroups = groupLayout.createSequentialGroup();
        
        // add each column horizontally.
        
        // first column
        columnGroups.addGroup(groupLayout.createParallelGroup()
                  .addComponent(fNameLbl)
                  .addComponent(lNameLbl));
        
        // second column
        columnGroups.addGroup(groupLayout.createParallelGroup()
                  .addComponent(fNameFld)
                  .addComponent(lNameFld)
                    .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                        .addComponent(saveButt) // group pushing button to right
                    )
                );
        
        // set horizontal groups
        groupLayout.setHorizontalGroup(columnGroups);

        // create a sequence of row groups.
        GroupLayout.SequentialGroup rowGroups = groupLayout.createSequentialGroup();
        
        // add each row vertically.
        
        // first row
        rowGroups.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                 .addComponent(fNameLbl)
                 .addComponent(fNameFld));
        
        // second row
        rowGroups.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                 .addComponent(lNameLbl)
                 .addComponent(lNameFld)
        );
        
        // third row
        rowGroups.addGroup(groupLayout.createSequentialGroup()
                    .addComponent(saveButt)
        );
        
        // set vertical groups
        groupLayout.setVerticalGroup(rowGroups);

    }

    public static void main(String[] args) {
        final JPanel c = new LayingOutComponentsOnGuiGroupLayout();
        c.setPreferredSize(new Dimension(380, 118));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-4 Laying Out Components on GUI", c);
    }
}