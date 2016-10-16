package org.java7recipes.chapter14.recipe14_16;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Adding Component to GUI.
 * @author cdea
 */
public class KeyboardShortcuts extends JPanel implements AppSetup{

    public KeyboardShortcuts(){   
       
    }
    
    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        JMenuItem newItem = new JMenuItem("New", null);
        newItem.setMnemonic(KeyEvent.VK_N);
        
        AbstractAction newAction = new AbstractAction("New") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New option selected");
            }
        };
        newAction.putValue(AbstractAction.MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
        newItem.addActionListener(newAction);
        menu.add(newItem);
        
        JButton button = new JButton(newAction);
        frame.getContentPane().add(button, BorderLayout.NORTH);
        
        JMenuItem saveItem = new JMenuItem("Save", null);
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Saved..");
            }
        });
        menu.add(saveItem); 
        
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
        final JPanel c = new KeyboardShortcuts();
        c.setPreferredSize(new Dimension(433, 312));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-16 Creating Keyboard Shortcuts", c);
    }
}
