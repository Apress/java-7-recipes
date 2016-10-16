package org.java7recipes.chapter12.recipe12_14;

import java.awt.event.ActionEvent;
import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.java7recipes.chapter12.AppSetup;

/**
 * Printing Documents.
 * 
 * @author cdea
 */
public class PrintingDocuments extends JComponent implements AppSetup, Printable {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());

        // antialising
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Serif with drop shadow
        Font serif = new Font("Serif", Font.PLAIN, 30);
        g2d.setFont(serif);
        g2d.setPaint(new Color(50, 50, 50, 150));
        g2d.drawString("Java 7 Recipes", 52, 52);
        // paint red 
        g2d.setPaint(Color.RED);
        g2d.drawString("Java 7 Recipes", 50, 50);


        // SanSerif
        g2d.setPaint(Color.BLUE);
        Font sanSerif = new Font("SanSerif", Font.PLAIN, 30);
        g2d.setFont(sanSerif);
        g2d.drawString("Java 7 Recipes", 50, 100);

        // Dialog
        g2d.setPaint(Color.GREEN);
        Font dialog = new Font("Dialog", Font.PLAIN, 30);
        g2d.setFont(dialog);
        g2d.drawString("Java 7 Recipes", 50, 150);

        // Monospaced
        g2d.setPaint(Color.BLACK);
        Font monospaced = new Font("Monospaced", Font.PLAIN, 30);
        g2d.setFont(monospaced);
        g2d.drawString("Java 7 Recipes", 50, 200);
    }

    @Override
    public int print(Graphics g, PageFormat pgFormat, int page) throws
            PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(pgFormat.getImageableX(), pgFormat.getImageableY());

        draw(g2d);

        return PAGE_EXISTS;
    }
    
    public void apply(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem printMenuItem = new JMenuItem("Print...");
        final Printable printSurface = this;
        printMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(printSurface);
                boolean ok = job.printDialog();
                if (ok) {
                 try {
                      job.print();
                 } catch (PrinterException ex) {
                     ex.printStackTrace();
                 }
                }
            }
        });
        menu.add(printMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }
    public static void main(String[] args) {
        final PrintingDocuments c = new PrintingDocuments();
        c.setPreferredSize(new Dimension(330, 217));
        SimpleAppLauncher.launch("Chapter 12-10 Printing Documents", c);
    }
}