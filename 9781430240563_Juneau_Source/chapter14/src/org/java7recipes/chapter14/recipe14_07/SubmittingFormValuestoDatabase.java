package org.java7recipes.chapter14.recipe14_07;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import org.java7recipes.chapter14.CellConstraint;
import org.java7recipes.chapter14.CustomGridLayout;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * <p>
 * +------------------------+
 * | [label ] [   field   ] |
 * | [label ] [   field   ] |
 * |             [ button ] |
 * +------------------------+
 * </p>
 * 
 * Submitting Form Values to Database.
 * @author cdea
 */
public class SubmittingFormValuestoDatabase extends JPanel {

    public SubmittingFormValuestoDatabase(){
        JLabel fNameLbl = new JLabel("First Name");
        final JTextField fNameFld = new JTextField(20);
        JLabel lNameLbl = new JLabel("Last Name");
        final JTextField lNameFld = new JTextField(20);
        final JButton saveButt = new JButton("Save");
        
        // Call Swing Worker to save to database.
        saveButt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveButt.setEnabled(false);
                SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
                    
                    @Override
                    protected Integer doInBackground() throws Exception {
                        int pk = DBUtils.saveContact(fNameFld.getText(), lNameFld.getText());
                        return pk;
                    }

                    @Override
                    protected void done() {
                        try {
                            System.out.println("Primary key = " + get());
                        } catch(InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        saveButt.setEnabled(true);
                    }
                };
                worker.execute();
            }
        });


        // create a layout 2 columns and 3 rows
        // horizontal and vertical gaps between components are 5 pixels
        CustomGridLayout cglayout = new CustomGridLayout(5, 5, 2, 3);   
        
        setLayout(cglayout);
        
        // add first name label cell 0,0
        addToPanel(fNameLbl, 0, 0);        

        // add last name label cell 0,1
        addToPanel(lNameLbl, 0, 1);

        // add first name field cell 1,0
        addToPanel(fNameFld, 1, 0);

        // add last name field cell 1,1
        addToPanel(lNameFld, 1, 1);        
        
        // add save button and shift to the right
        CellConstraint saveButtConstr = new CellConstraint()
                .setColNum(1)
                .setRowNum(2)
                .setAlign(CellConstraint.RIGHT);
        add(saveButt, saveButtConstr);

    }

    private void addToPanel(Component comp, int colNum, int rowNum) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum);
        add(comp, constr);
    }
    public static void main(String[] args) {
        final JPanel c = new SubmittingFormValuestoDatabase();
        c.setPreferredSize(new Dimension(402, 118));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-7 Submitting Form Values to Database.", c);
    }
}