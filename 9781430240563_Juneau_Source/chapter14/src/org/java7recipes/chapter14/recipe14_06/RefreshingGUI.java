package org.java7recipes.chapter14.recipe14_06;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Refreshing a GUI.
 * requires jdk7
 * @author cdea
 */
public class RefreshingGUI extends JPanel {

    static SwingWorker<Boolean, String> copyWorker;
    final int numFiles = 30;
    
    public RefreshingGUI() {

        setLayout(new BorderLayout());

        JPanel topArea = new JPanel();

        // progress bar 
        final JLabel label = new JLabel("Files Transfer:", JLabel.CENTER);
        topArea.add(label);

        // progress bar 
        final JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(numFiles);


        topArea.add(progressBar);

        // create the top area
        add(topArea, BorderLayout.NORTH);

        // build buttons start and cancel
        JPanel buttonsArea = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton startButton = new JButton("Start");
        final JButton cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        buttonsArea.add(startButton);
        buttonsArea.add(cancelButton);
        
        // build status area
        final JTextArea textArea = new JTextArea(5, 15);
        textArea.setEditable(false);
        JScrollPane statusScroll = new JScrollPane(textArea);
        buttonsArea.add(statusScroll);

        // create the buttons area
        add(buttonsArea, BorderLayout.SOUTH);
        
        
        // spawn a worker thread 
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                progressBar.setValue(0);
                textArea.setText("");
                cancelButton.setEnabled(true);
                copyWorker = createWorker(numFiles, startButton, cancelButton, textArea, progressBar);
                copyWorker.execute();
            }
        });
        
        // cancel button will kill worker and reset.
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                cancelButton.setEnabled(false);
                copyWorker.cancel(true);
                progressBar.setValue(0);
                
            }
        });

    }
    
    
    public SwingWorker<Boolean, String> createWorker(final int numFiles, 
            final JButton startButton, 
            final JButton cancelButton, 
            final JTextArea status, 
            final JProgressBar pBar){
        
            return new SwingWorker<Boolean, String>() {

            /**
             * Not on the EDT 
             */
            @Override
            protected Boolean doInBackground() throws Exception {
                for (int i = 0; i < numFiles; i++) {
                    long elapsedTime = System.currentTimeMillis();
                    copyFile("some file", "some dest file");
                    elapsedTime = System.currentTimeMillis() - elapsedTime;
                    String status = elapsedTime + " milliseconds";
                    // queue up status
                    publish(status);
                }
                return true;
            }

            /**
             * On the EDT
             */
            @Override
            protected void process(List<String> chunks) {
                super.process(chunks);
                // with each update gui
                for (String chunk : chunks) {
                    status.append(chunk + "\n");
                    pBar.setValue(pBar.getValue() + 1);
                }
            }

            /**
             * On the EDT
             */
            @Override
            protected void done() {
                try {
                    if (isCancelled()) {
                        status.append("File transfer was cancelled.\n");    
                        return;     
                    }
                    Boolean ack = get();
                    if (Boolean.TRUE.equals(ack)) {
                        status.append("All files were transferred successfully.\n");
                    }
                    startButton.setEnabled(true);
                    cancelButton.setEnabled(false);
                } catch (InterruptedException ex) {
                    status.append("File transfer was interupted.\n");
                } catch (ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    public void copyFile(String src, String dest) throws InterruptedException {
        // simulate a long time
        Random rnd = new Random(System.currentTimeMillis());
        long millis = rnd.nextInt(1000);
        Thread.sleep(millis);
    }

    public static void main(String[] args) {
        final JPanel c = new RefreshingGUI();
        c.setPreferredSize(new Dimension(386, 160));
        c.setMinimumSize(new Dimension(386, 160));

        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-6 Refreshing the GUI", c);
    }
}
