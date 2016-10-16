package org.java7recipes.chapter12.recipe12_17;

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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.java7recipes.chapter12.AppSetup;

/**
 * Saving an Image.
 * 
 * @author cdea
 */
public class SavingAnImage extends JComponent implements AppSetup {

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

    @Override
    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

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

        JMenuItem saveAsMenuItem = new JMenuItem("Save Image As...");
        saveAsMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser saveImageDlg = new JFileChooser(new File(System.getProperty("user.home")));
                int response = saveImageDlg.showSaveDialog(frame);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File fileToSaveAs = saveImageDlg.getSelectedFile();
                    String fileName = fileToSaveAs.getName();
                    String fileType = null;
                    if (fileName.indexOf(".") > 0) {
                        fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();      
                    }
                    if (fileType != null && fileType.length() == 4 && image != null) {
                        try {
                            BufferedImage bi = image; // retrieve image
                            switch(fileType){
                                case ".jpg":
                                case ".png":
                                case ".gif":
                                    ImageIO.write(bi, fileType.substring(1), fileToSaveAs);
                                    break;
                            }       
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        } 
                    } else {
                        // error
                        JOptionPane.showMessageDialog(frame, "Sorry couldn't save. \nTry loading an image and saving with a file name using extension as: .gif .jpg or .png");
                    }
                }
            }
        });
        menu.add(saveAsMenuItem);
        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        final SavingAnImage c = new SavingAnImage();
        c.setPreferredSize(new Dimension(374, 415));
        SimpleAppLauncher.launch("Chapter 12-17 Saving an Image", c);
    }
}