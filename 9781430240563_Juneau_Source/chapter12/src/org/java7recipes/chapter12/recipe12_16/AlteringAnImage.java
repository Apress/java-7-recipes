package org.java7recipes.chapter12.recipe12_16;

import org.java7recipes.chapter12.SimpleAppLauncher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingWorker;
import org.java7recipes.chapter12.AppSetup;

/**
 * Altering an Image.
 * 
 * @author cdea
 */
public class AlteringAnImage extends JComponent implements AppSetup {
    private static BufferedImage image = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getParent().getWidth(), getParent().getHeight());
        if (image != null) {
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);        
        }
    }
    
    public void alterImage(BufferedImage image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        int[] argbData; // Array holding the ARGB data

        // Prepare the ARGB array
        argbData = new int[width * height];

        // Grab the ARGB data
        image.getRGB(0, 0, width, height, argbData, 0, width);
        
   
        // Loop through each pixel in the array
        for (int i = 0 ; i < argbData.length ; i++) {
            argbData[i] = (argbData[i] & 0xFF00FF00);
        }

        //  Set the return Bitmap to use this altered ARGB array
        image.setRGB(0, 0, width, height, argbData, 0, width);         
    }
    
    @Override
    public void apply(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Image...");

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final FileDialog loadImageDlg = new FileDialog((JFrame) null);
                loadImageDlg.setVisible(true);
                if (loadImageDlg.getFile() != null) {
                    SwingWorker<BufferedImage, Void> worker = new SwingWorker<BufferedImage, Void>() {
                        @Override
                        protected BufferedImage doInBackground() throws Exception {
                            try {
                                File imageFile = new File(loadImageDlg.getDirectory() + File.separator + loadImageDlg.getFile());
                                image = ImageIO.read(imageFile);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            alterImage(image);
                            return image;
                        }

                        @Override
                        protected void done() {
                            try {
                                image = get();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            repaint();
                        }
                    };
                    worker.execute();
                }
            }
        });
        menu.add(loadMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        
    }
    public static void main(String[] args) {
        final AlteringAnImage c = new AlteringAnImage();
        c.setPreferredSize(new Dimension(374, 415));
        SimpleAppLauncher.launch("Chapter 12-16 Altering an Image", c);
    }
}