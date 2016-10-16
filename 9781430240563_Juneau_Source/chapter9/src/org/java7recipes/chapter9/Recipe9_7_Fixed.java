package org.java7recipes.chapter9;

import sun.awt.WindowIDProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/19/11
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe9_7_Fixed implements ActionListener {
    public static void main(String[] args) {
        Recipe9_7_Fixed recipe = new Recipe9_7_Fixed();
        recipe.start();
    }

    private void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                startFrame();
            }


        });

    }

    Set<JFrame> createdWindows = new HashSet<JFrame>();
    private void startFrame() {
        JFrame frame = new JFrame();
        JButton createButton = new JButton("Let's create new windows");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewWindow();
            }
        });

        JButton closeButton = new JButton("Let's close the windows");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JFrame frame : createdWindows) {
                    frame.dispose();
                }
                createdWindows.clear();
            }
        });
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createButton,BorderLayout.CENTER);
        mainPanel.add(closeButton,BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    AtomicLong windowID = new AtomicLong();

    private void createNewWindow() {
        JFrame windowFrame = new JFrame("Window frame ");
        JButton contentButton = new JButton("Window # "+ windowID.incrementAndGet());
        contentButton.addActionListener(this);
        Image someImage = new BufferedImage(900,500,BufferedImage.TYPE_INT_ARGB);
        windowFrame.setIconImage(someImage);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(contentButton);
        windowFrame.setContentPane(contentPanel);
        windowFrame.setSize(100, 100);
        windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        windowFrame.setVisible(true);

        createdWindows.add(windowFrame);


    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Someone clicke a button on a window");
    }
}
