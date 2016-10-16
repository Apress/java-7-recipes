package org.java7recipes.chapter14.recipe14_08;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Random;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Making a Multi-Window Program.
 * @author cdea
 */
public class MultiWindowGUI extends JDesktopPane implements AppSetup {

    public MultiWindowGUI() {
        setDragMode(JDesktopPane.LIVE_DRAG_MODE);
        //setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newWindowMenuItem = new JMenuItem("New Internal Frame");
        newWindowMenuItem.setMnemonic(KeyEvent.VK_N);
        newWindowMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        final JDesktopPane desktop = this;
        newWindowMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame frame = new InternalFrame();
                frame.setVisible(true);
                desktop.add(frame);
                try {
                    frame.setSelected(true);
                } catch (PropertyVetoException pve) {
                }
            }
        });
        menu.add(newWindowMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        final JDesktopPane c = new MultiWindowGUI();
        c.setPreferredSize(new Dimension(433, 312));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-8 Making a Multi-Window Program", c);
    }
}

class InternalFrame extends JInternalFrame {

    static int count = 0;
    static final int xOffset = 35;
    static final int yOffset = 35;
    final static String[] rndQuotes = {
        "Even in laughter the heart is sorrowful",
        "For what does it profit a man to gain the whole world, and forfeit his soul?",
        "The light of the body is the eye; if then your eye is true, all your body will be full of light.",
        "For many are called, but few are chosen.",
        "A word fitly spoken is like apples of gold in pictures of silver.",
        "Iron sharpeneth iron; so a man sharpeneth the countenance of his friend."
    };

    public InternalFrame() {
        super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        Random rand = new Random();
        int q = rand.nextInt(rndQuotes.length);

        setLayout(new BorderLayout());
        JTextArea ta = new JTextArea(rndQuotes[q]);
        JScrollPane sp = new JScrollPane(ta);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        add(sp, BorderLayout.CENTER);
        setSize(200, 100);

        // Stagger windows
        setLocation(xOffset * count, yOffset * count);
    }
}