package org.java7recipes.chapter14.recipe14_11;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Drawing on a Canvas.
 * @author cdea
 */
public class DrawOnCanvas extends JPanel implements MouseListener,
        MouseMotionListener {

    Path2D oneDrawing = new Path2D.Double();
    List<Path2D> drawings = new ArrayList<>();
    private Point2D anchorPt;

    public DrawOnCanvas() {
        add(new JLabel("Java 7"));
        JTextField field = new JTextField(10);
        add(field);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        if (oneDrawing != null) {
            g2d.draw(oneDrawing);
        }
        for (Path2D gp : drawings) {
            g2d.draw(gp);
        }

    }

    public static void main(String[] args) {
        final DrawOnCanvas c = new DrawOnCanvas();
        c.addMouseListener(c);
        c.addMouseMotionListener(c);
        c.setPreferredSize(new Dimension(409, 726));
        SimpleAppLauncher.launch("Chapter 12-11 Drawing on a Canvas", c);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        anchorPt = (Point2D) e.getPoint().clone();
        oneDrawing = new GeneralPath();
        oneDrawing.moveTo(anchorPt.getX(), anchorPt.getY());
        repaint();
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (anchorPt != null) {
            drawings.add(oneDrawing);
            oneDrawing = null;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        oneDrawing.lineTo(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}