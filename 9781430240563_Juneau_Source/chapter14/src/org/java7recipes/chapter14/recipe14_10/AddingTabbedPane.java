package org.java7recipes.chapter14.recipe14_10;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Adding tabs to an application.
 * Requires jdk7
 * @author cdea
 */
public class AddingTabbedPane extends JTabbedPane implements AppSetup{

    public AddingTabbedPane(){
        
       
       for (int i=0; i<10; i++) {
           JPanel tabPane = new JPanel();
           tabPane.add(new JLabel("Tab" + i));
           addTab("Tab " + i, null, tabPane, "Tab" + i);           
       }
       setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Tabbed Panels");
        JMenuItem left = new JMenuItem("Left", null);
        left.addActionListener(new TabPlacementAction(this, "left"));
        menu.add(left);

        JMenuItem right = new JMenuItem("Right", null);
        right.addActionListener(new TabPlacementAction(this, "right"));
        menu.add(right);

        JMenuItem top = new JMenuItem("Top", null);
        top.addActionListener(new TabPlacementAction(this, "top"));
        menu.add(top);
        
        JMenuItem bottom = new JMenuItem("Bottom", null);
        bottom.addActionListener(new TabPlacementAction(this, "bottom"));
        menu.add(bottom);
        
        menuBar.add(menu);
        
        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        final JComponent c = new AddingTabbedPane();
        c.setPreferredSize(new Dimension(433, 312));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-10 Adding Tabs to Forms ", c);
    }
}

class TabPlacementAction implements ActionListener {
    private String action;
    private JTabbedPane tabbedPane;
    public TabPlacementAction (JTabbedPane tabbedPane, String action) {
        this.action = action;
        this.tabbedPane = tabbedPane;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("left".equalsIgnoreCase(action)) {
            tabbedPane.setTabPlacement(JTabbedPane.LEFT);        
        } else if ("right".equalsIgnoreCase(action)) {
            tabbedPane.setTabPlacement(JTabbedPane.RIGHT);        
        }  else if ("top".equalsIgnoreCase(action)) {
            tabbedPane.setTabPlacement(JTabbedPane.TOP);        
        } else if ("bottom".equalsIgnoreCase(action)) {
            tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);        
        }
    }
}
