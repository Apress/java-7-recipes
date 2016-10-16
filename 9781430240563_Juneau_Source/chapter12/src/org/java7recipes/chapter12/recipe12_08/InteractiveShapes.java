package org.java7recipes.chapter12.recipe12_08;

import java.awt.event.MouseEvent;
import java.awt.BasicStroke;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * Interactive shapes.
 * @author cdea
 */
public class InteractiveShapes extends JComponent implements MouseListener,
        MouseMotionListener {

    private boolean selectedShape;
    private boolean hoveredShape;
    private QuadCurve2D s;
    private Point2D translatePt;
    private Point2D anchorPt;
    private AffineTransform moveTranslate = new AffineTransform();
    private int moveType = -1;
    public static final int START_PT = 1;
    public static final int CNTRL_PT = 2;
    public static final int END_PT = 3;
    public static final int MOVE_RECT = 4;

    public InteractiveShapes() {
        s = new QuadCurve2D.Float(50, 50,
                125, 150,
                150, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawString("Bounded Rectangle " + s.getBounds2D().getX() + ", " + 
                s.getBounds2D().getY(), 10, 10);
        AffineTransform origTransform = g2d.getTransform();
        
        // selected and move shape
        if (selectedShape && translatePt != null && moveType == MOVE_RECT) {
            
            // move the shape
            moveTranslate.setToTranslation(translatePt.getX() - anchorPt.getX(),
                    translatePt.getY() - anchorPt.getY());
            g2d.setTransform(moveTranslate);
            
        }

        // set color and thickness of stroke
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

        // Draw the quad curve shape
        g2d.draw(s);

        // hovering over shape (gray dotted box)
        if (hoveredShape) {
            g2d.setColor(Color.LIGHT_GRAY);
            final float dash[] = {2, 2};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, dash, 0));
            g2d.draw(s.getBounds2D());

        }

        // selected shape
        if (selectedShape) {

            // draw red dotted box
            g2d.setColor(Color.RED);
            final float dash[] = {2, 2};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, dash, 2));
            g2d.draw(s.getBounds2D());

            // draw ctrl point rect
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            Rectangle2D ctrl1Rect = new Rectangle2D.Double(
                    s.getCtrlPt().getX() - 2, s.getCtrlY() - 2, 5, 5);
            g2d.draw(ctrl1Rect);

            // draw starting point rect
            Rectangle2D startPtRect = new Rectangle2D.Double(
                    s.getX1() - 2, s.getY1() - 2, 5, 5);
            g2d.setPaint(Color.WHITE);
            g2d.fill(startPtRect);
            g2d.setPaint(Color.BLACK);
            g2d.draw(startPtRect);

            // draw end point rect
            Rectangle2D endPtRect = new Rectangle2D.Double(
                    s.getX2() - 2, s.getY2() - 2, 5, 5);
            g2d.setPaint(Color.WHITE);
            g2d.fill(endPtRect);
            g2d.setPaint(Color.BLACK);
            g2d.draw(endPtRect);

        }

        // reset
        g2d.setTransform(origTransform);

    }

    public static void main(String[] args) {
        final InteractiveShapes c = new InteractiveShapes();
        c.addMouseListener(c);
        c.addMouseMotionListener(c);
        c.setPreferredSize(new Dimension(409, 726));
        SimpleAppLauncher.launch("Chapter 12-8 Interactive Shapes", c);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        boolean anySelected = false;
        
        if (selectedShape) {
            // is control point position handle selected?
            Rectangle2D ctrl1Rect = new Rectangle2D.Double(
                    s.getCtrlX() - 2, s.getCtrlY() - 2, 5, 5);
            if (ctrl1Rect.contains(e.getPoint())) {
                moveType = CNTRL_PT;
                repaint();
                return;
            }

            // is start point position handle selected?
            Rectangle2D startRect = new Rectangle2D.Double(
                    s.getX1() - 2, s.getY1() - 2, 5, 5);
            if (startRect.contains(e.getPoint())) {
                moveType = START_PT;
                repaint();
                return;
            }

            // is end point position handle selected?
            Rectangle2D endRect = new Rectangle2D.Double(
                    s.getX2() - 2,
                    s.getY2() - 2, 5, 5);
            if (endRect.contains(e.getPoint())) {
                moveType = END_PT;
                repaint();
                return;
            }

            // is mouse inside shape
            if (s.contains(e.getPoint())) {
                moveType = MOVE_RECT;
                anchorPt = (Point2D) e.getPoint().clone();
                repaint();
                return;
            }
        }

        // select shape
        if (s.contains(e.getPoint()) && !selectedShape) {
            selectedShape = true;
            anySelected = true;
        }

        if (!anySelected) {
            selectedShape = false;
        }
        
        repaint();
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        moveType = -1;
        if (anchorPt != null) {
            double dx = e.getPoint().getX() - anchorPt.getX();
            double dy = e.getPoint().getY() - anchorPt.getY();
         
            // update all points in shape
            s.setCurve(s.getX1() + dx, 
                    s.getY1() + dy,
                    s.getCtrlX() + dx, 
                    s.getCtrlY() + dy, 
                    s.getX2() + dx, 
                    s.getY2() + dy);
            
            // reset for subsequent drag operation
            anchorPt = null;
            translatePt = null;
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
        if (selectedShape) {
            switch (moveType) {
                case START_PT:
                    s.setCurve(e.getPoint(), s.getCtrlPt(), s.getP2());
                    break;
                case CNTRL_PT:
                    s.setCurve(s.getP1(), e.getPoint(), s.getP2());
                    break;
                case END_PT:
                    s.setCurve(s.getP1(), s.getCtrlPt(), e.getPoint());
                    break;
                case MOVE_RECT:
                    translatePt = e.getPoint();
                    break;
            }
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // move over shape
        if (s.contains(e.getPoint()) && !hoveredShape) {
            hoveredShape = true;
        }
        
        // move away from shape
        if (!s.contains(e.getPoint()) && hoveredShape) {
            hoveredShape = false;
        }
        repaint();
    }
}