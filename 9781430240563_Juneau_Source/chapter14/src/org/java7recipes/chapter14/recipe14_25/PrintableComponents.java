package org.java7recipes.chapter14.recipe14_25;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Printable components.
 * @author cdea
 */
public class PrintableComponents extends JPanel {

    public PrintableComponents(){
        setLayout(new BorderLayout(5, 5));
        String[][] data = {
            {"", "", "8", "8", "8", "9", "7"},
            {"", "", "9", "7", "8", "8", "8"},
            {"", "", "8", "8", "8", "9", "6"},
            {"", "", "8", "8.5", "8", "9", "8"},
            {"", "", "8.5", "8.5", "8", "9", "8"}
        };
        String[] colHeaders = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
        final JTable timeSheet = new JTable(data, colHeaders);
       
        JScrollPane sp = new JScrollPane(timeSheet);
       
        add(sp, BorderLayout.CENTER);
        
        JButton printButton = new JButton("Print");
        add(printButton, BorderLayout.SOUTH);
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeSheet.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        final JPanel c = new PrintableComponents();
        c.setPreferredSize(new Dimension(384, 45));
        c.setMinimumSize(new Dimension(384, 277));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-25 Printable Components", c);
    }
}