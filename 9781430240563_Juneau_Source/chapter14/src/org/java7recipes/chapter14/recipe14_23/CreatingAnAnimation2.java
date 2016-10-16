package org.java7recipes.chapter14.recipe14_23;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.java7recipes.chapter14.AppSetup;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Creating an Animation 2.
 * @author cdea
 */
public class CreatingAnAnimation2 extends JPanel implements AppSetup {

    JFrame frameParent;
    boolean focusWindow = false;
    private static float EXIT_WINDOW_OPACITY = .60f;

    public CreatingAnAnimation2() {

        // save button
        final JButton animButton = new JButton("<html><font size=+2>Press Me!</font>");
        animButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(animButton);


    }

    @Override
    public void apply(JFrame frame) {
        frameParent = frame;
        frame.setUndecorated(true);
        frame.setOpacity(EXIT_WINDOW_OPACITY);

        frame.addMouseListener(new MouseAdapter() {

            int animDuration = 400;
            long startTime;
            Timer timer = new Timer(30, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    long currentTime = System.nanoTime() / 1000000;
                    long totalTime = currentTime - startTime;
                    if (totalTime > animDuration) {
                        startTime = currentTime;
                        timer.stop();
                        return;
                    }

                    // interpolate 
                    float fraction = (float) totalTime / animDuration;
                    if (frameParent != null) {
                        float newOpacity = frameParent.getOpacity();
                        if (focusWindow) {
                            newOpacity += fraction;
                        } else {
                            newOpacity -= fraction;
                        }
                        if (newOpacity > 1f) {
                            newOpacity = 1f;
                            timer.stop();
                        } else if (newOpacity < EXIT_WINDOW_OPACITY) {
                            newOpacity = EXIT_WINDOW_OPACITY;
                            timer.stop();
                        }
                        frameParent.setOpacity(newOpacity);

                    }
                    repaint();
                }
            });

            @Override
            public void mouseEntered(MouseEvent e) {
                focusWindow = true;
                startTime = System.nanoTime() / 1000000;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                focusWindow = false;
                startTime = System.nanoTime() / 1000000;
                timer.start();
            }
        });

    }

    public static void main(String[] args) {
        final JPanel c = new CreatingAnAnimation2();
        c.setPreferredSize(new Dimension(384, 100));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-23 Creating an Animation 2.", c);
    }
}
