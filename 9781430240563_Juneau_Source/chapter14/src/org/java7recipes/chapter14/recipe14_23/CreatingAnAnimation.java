package org.java7recipes.chapter14.recipe14_23;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Creating an Animation.
 * @author cdea
 */
public class CreatingAnAnimation extends JPanel {

    public CreatingAnAnimation() {

        // glow button
        final JButton animButton = new JButton("<html><font size=+2>Press Me!</font>");
        animButton.addMouseListener(new MouseAdapter() {

            Color startColor = Color.BLACK;
            Color endColor = Color.RED;
            Color currentColor = startColor;
            int animDuration = 250;
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
                    int red = (int) ((1 - fraction) * startColor.getRed() + fraction * endColor.getRed());
                    int green = (int) ((1 - fraction) * startColor.getGreen() + fraction * endColor.getGreen());
                    int blue = (int) ((1 - fraction) * startColor.getBlue() + fraction * endColor.getBlue());
                    currentColor = new Color(red, green, blue);
                    animButton.setForeground(currentColor);

                    repaint();
                }
            });

            @Override
            public void mouseEntered(MouseEvent e) {
                currentColor = startColor;
                startTime = System.nanoTime() / 1000000;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentColor = startColor;
                animButton.setForeground(currentColor);
                repaint();
                timer.stop();
            }
        });

        add(animButton);
    }

    public static void main(String[] args) {
        final JPanel c = new CreatingAnAnimation();
        c.setPreferredSize(new Dimension(384, 100));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-23 Creating an Animation.", c);
    }
}

class AnimatedButton1 extends JButton {
}