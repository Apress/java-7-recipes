package org.java7recipes.chapter14.recipe14_18;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.CellConstraint;
import org.java7recipes.chapter14.CustomGridLayout;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Developing A Dialog
 * @author cdea
 */
public class DevelopingADialog extends JPanel implements AppSetup {

    static JDialog LOGIN_DIALOG;

    public void apply(final JFrame frame) {

        if (LOGIN_DIALOG == null) {
            LOGIN_DIALOG = new MyDialog(frame);
        }

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Home");
        JMenuItem newItem = new JMenuItem("Change Password", null);
        newItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOGIN_DIALOG.pack();
                
                // center dialog
                Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
                int scrnWidth = LOGIN_DIALOG.getSize().width;
                int scrnHeight = LOGIN_DIALOG.getSize().height;
                int x = (scrnSize.width - scrnWidth) / 2;
                int y = (scrnSize.height - scrnHeight) / 2;

                // Move the window
                LOGIN_DIALOG.setLocation(x, y);
                LOGIN_DIALOG.setResizable(false);
                LOGIN_DIALOG.setVisible(true);
            }
        });

        menu.add(newItem);

        menu.addSeparator();

        JRadioButtonMenuItem nonModalItem = new JRadioButtonMenuItem("Non Modal", null);

        nonModalItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOGIN_DIALOG.setModal(false);
            }
        });
        menu.add(nonModalItem);
        ButtonGroup modalGroup = new ButtonGroup();
        modalGroup.add(nonModalItem);

        JRadioButtonMenuItem modalItem = new JRadioButtonMenuItem("Modal", null);
        modalItem.setSelected(true);
        modalItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOGIN_DIALOG.setModal(true);
            }
        });
        menu.add(modalItem);
        modalGroup.add(modalItem);

        menu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit", null);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitItem);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }
    

    public static void main(String[] args) {
        final JPanel c = new DevelopingADialog();
        c.setPreferredSize(new Dimension(433, 312));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-18 Developing a Dialog", c);
    }
}

class MyDialog extends JDialog {

    public MyDialog(Frame owner) {
        super(owner, true);
        CustomGridLayout cglayout = new CustomGridLayout(20, 20, 2, 4);

        setLayout(cglayout);
        JLabel mainLabel = new JLabel("Enter User Name & Password");
        addToPanel(mainLabel, 1, 0, CellConstraint.CENTER);
      
        JLabel userNameLbl = new JLabel("User Name: ");
        addToPanel(userNameLbl, 0, 1, CellConstraint.RIGHT);  
        
        JLabel passwordLbl = new JLabel("Password: ");
        addToPanel(passwordLbl, 0, 2, CellConstraint.RIGHT); 
        
        // username text field
        final JTextField userNameFld = new JTextField("Admin", 20);
        addToPanel(userNameFld, 1, 1, CellConstraint.LEFT); 
        
        // password field
        final JPasswordField passwordFld = new JPasswordField("drowssap", 20);
        addToPanel(passwordFld, 1, 2, CellConstraint.LEFT); 
        
        JButton login = new JButton("Change");
        addToPanel(login, 1, 3, CellConstraint.RIGHT);
        
        final JDialog dialog = this; 
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        

    }
    
    private void addToPanel(Component comp, int colNum, int rowNum, int alignment) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum)
                .setAlign(alignment);
        add(comp, constr);
    }
}