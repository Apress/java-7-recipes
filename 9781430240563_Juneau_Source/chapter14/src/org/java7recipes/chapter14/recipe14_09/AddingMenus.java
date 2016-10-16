package org.java7recipes.chapter14.recipe14_09;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Adding Menus to an application.
 * @author cdea
 */
public class AddingMenus extends JPanel implements AppSetup{

    public AddingMenus(){   
       
    }
    
    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New", null);
        menu.add(newItem);
        
        JMenuItem saveItem = new JMenuItem("Save", null);
        saveItem.setEnabled(false);
        menu.add(saveItem); 
        
        menu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Exit", null);
        menu.add(exitItem); 
        
        menuBar.add(menu);
        
        JMenu tools = new JMenu("Cameras");
        JCheckBoxMenuItem showCamera1= new JCheckBoxMenuItem("Show Camera 1", null);
        showCamera1.setSelected(true);
        tools.add(showCamera1);
        
        JCheckBoxMenuItem showCamera2= new JCheckBoxMenuItem("Show Camera 2", null);
        tools.add(showCamera2);
        menuBar.add(tools);
        
        JMenu alarm = new JMenu("Alarm");
        ButtonGroup alarmGroup = new ButtonGroup();
        JRadioButtonMenuItem alertItem = new JRadioButtonMenuItem("Sound Alarm");
        alarm.add(alertItem);
        alarmGroup.add(alertItem);
        
        JRadioButtonMenuItem stopItem = new JRadioButtonMenuItem("Alarm Off", null);
        stopItem.setSelected(true);
        alarm.add(stopItem);
        alarmGroup.add(stopItem);
        
        JMenu contingencyPlans = new JMenu("Contingent Plans");
        JCheckBoxMenuItem selfDestruct = new JCheckBoxMenuItem("Self Destruct in T minus 50");
        contingencyPlans.add(selfDestruct);
        
        JCheckBoxMenuItem turnOffCoffee = new JCheckBoxMenuItem("Turn off the coffee machine ");
        contingencyPlans.add(turnOffCoffee);
        
        JCheckBoxMenuItem runOption= new JCheckBoxMenuItem("Run for your lives! ");
        contingencyPlans.add(runOption);
        
        
        alarm.add(contingencyPlans);
        
        menuBar.add(alarm);
        
        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        final JPanel c = new AddingMenus();
        c.setPreferredSize(new Dimension(433, 312));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-9 Adding Menus to an Application", c);
    }
}
