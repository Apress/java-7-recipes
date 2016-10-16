package org.java7recipes.chapter14.recipe14_21;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import org.java7recipes.chapter14.CellConstraint;
import org.java7recipes.chapter14.CustomGridLayout;
import org.java7recipes.chapter14.SimpleAppLauncher;



/**
 * Adding a Listener to Document.
 * @author cdea
 */
public class ChangingLookNFeel extends JPanel {

    public ChangingLookNFeel() {
        

        CustomGridLayout cglayout = new CustomGridLayout(5, 5, 1, 7);   
        
        setLayout(cglayout);
        
        JLabel mainLabel = new JLabel("Setting Look N Feel : " + UIManager.getLookAndFeel().getName());
        addToPanel(mainLabel, 0, 0);
        
        JTextField textField = new JTextField(10);
        addToPanel(textField, 0, 1);
        
        JButton button = new JButton("Button");
        addToPanel(button, 0, 2);
        
        JList list = new JList(new String[] {"Carl", "Jonathan", "Joshua", "Mark", "John", "Paul", "Ringo", "George"} );
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(200, 100));
        addToPanel(listScrollPane, 0, 3);
        
        JCheckBox checkBox = new JCheckBox("Check box control");
        addToPanel(checkBox, 0, 4);
        
        String[][] data = {{"", "", "8", "8", "8", "9", "7"},
            {"", "", "9", "7", "8", "8", "8"},
            {"", "", "8", "8", "8", "9", "6"},
            {"", "", "8", "8.5", "8", "9", "8"},
            {"", "", "8.5", "8.5", "8", "9", "8"},
            {"", "", "8.5", "8.5", "8", "9", "8"},
            {"", "", "8.5", "8.5", "8", "9", "8"},
            {"", "", "8.5", "8.5", "8", "9", "8"}
        };
        String[] colHeaders = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
        JTable table = new JTable(data, colHeaders);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(300, 150));

        addToPanel(tableScrollPane, 0, 5);
        
        JTree tree = new JTree();
        JScrollPane tScrollPane = new JScrollPane(tree);
        tScrollPane.setPreferredSize(new Dimension(200, 150));
        addToPanel(tScrollPane, 0, 6);
        

    }
    
    private void addToPanel(Component comp, int colNum, int rowNum) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum);
        add(comp, constr);
    }

    public static void main(String[] args) {
        try {
            String lnf = null;
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equalsIgnoreCase(info.getName())) {
                    lnf = info.getClassName();
                    UIManager.setLookAndFeel(lnf);
                    break;
                }
            }
            if (lnf == null) {
                 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        final JPanel c = new ChangingLookNFeel();
        c.setPreferredSize(new Dimension(446, 505));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-21 Changing Look n Feel", c);
    }
}
