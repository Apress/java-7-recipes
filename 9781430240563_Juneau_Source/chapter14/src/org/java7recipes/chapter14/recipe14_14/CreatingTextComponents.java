package org.java7recipes.chapter14.recipe14_14;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import org.java7recipes.chapter14.CellConstraint;
import org.java7recipes.chapter14.CustomGridLayout;
import org.java7recipes.chapter14.SimpleAppLauncher;



/**
 * Creating Text Components.
 * @author cdea
 */
public class CreatingTextComponents extends JPanel {

    public CreatingTextComponents() {
        

        CustomGridLayout cglayout = new CustomGridLayout(5, 5, 2, 5);   
        
        setLayout(cglayout);
        
        JLabel mainLabel = new JLabel("Enter User Name & Password");
        addToPanel(mainLabel, 1, 0, CellConstraint.CENTER);
      
        JLabel userNameLbl = new JLabel("User Name: ");
        addToPanel(userNameLbl, 0, 1, CellConstraint.RIGHT);  
        
        JLabel passwordLbl = new JLabel("Password: ");
        addToPanel(passwordLbl, 0, 2, CellConstraint.RIGHT); 
        
        JLabel statusLbl = new JLabel("Status: ");
        addToPanel(statusLbl, 0, 4, CellConstraint.RIGHT); 
        
        // username text field
        final JTextField userNameFld = new JTextField("Admin", 20);
        addToPanel(userNameFld, 1, 1); 
        
        // password field
        final JPasswordField passwordFld = new JPasswordField("drowssap", 20);
        addToPanel(passwordFld, 1, 2); 
        
        JButton login = new JButton("Login");
        addToPanel(login, 1, 3, CellConstraint.RIGHT);

        // status text area
        final JTextArea taFld = new JTextArea(10, 20);
        JScrollPane statusScroll = new JScrollPane(taFld);
        taFld.setEditable(false);
        addToPanel(statusScroll, 1, 4); 
        
        login.setAction(new AbstractAction("login") {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Admin".equalsIgnoreCase(userNameFld.getText()) && 
                        Arrays.equals("drowssap".toCharArray(), passwordFld.getPassword())) {
                    taFld.append("Login successful\n");                
                } else {
                    taFld.append("Login failed\n");
                }
            }
        });

    }
    
    private void addToPanel(Component comp, int colNum, int rowNum) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum);
        add(comp, constr);
    }
    private void addToPanel(Component comp, int colNum, int rowNum, int alignment) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum)
                .setAlign(alignment);
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

        final JPanel c = new CreatingTextComponents();
        c.setPreferredSize(new Dimension(378, 359));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-14 Creating Text Components", c);
    }
}
