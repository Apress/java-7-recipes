
package org.java7recipes.chapter18.recipe18_18;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Recipe 18_18
 * 
 * This is the panel that contains the Swing components for the mail applet
 * 
 * @author juneau
 */
public class SwingMailPanel extends JPanel implements ActionListener {
    javax.swing.JTextField from;
    javax.swing.JTextField to;
    javax.swing.JTextField smtp;
    javax.swing.JTextArea message;
    javax.swing.JLabel mailLabel;
    javax.swing.JLabel fromLabel;
    javax.swing.JLabel toLabel;
    javax.swing.JLabel smtpLabel;
    javax.swing.JLabel blank;
    javax.swing.JLabel messageLabel;
    javax.swing.JButton messageButton;
    javax.swing.JScrollPane scrollpane;

    public SwingMailPanel(){
        
        messageButton = new javax.swing.JButton("Send");

        mailLabel = new javax.swing.JLabel("Please fill out the form below to send email.");
        fromLabel = new javax.swing.JLabel("From:");
        from = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel("To:");
        to = new javax.swing.JTextField();
        smtpLabel = new javax.swing.JLabel("SMTP Host:");
        smtp = new javax.swing.JTextField();
        messageLabel = new javax.swing.JLabel("Message:");
        message = new javax.swing.JTextArea(5,20);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        scrollpane = new JScrollPane(message);
        
        blank = new javax.swing.JLabel();

        // Set the layout
        this.setLayout(new GridLayout(11,2));

        // Add button to layout
        this.add(mailLabel);

        
        this.add(fromLabel);
        this.add(from);
       
        this.add(toLabel);
        this.add(to);
        
        this.add(smtpLabel);
        this.add(smtp);
        
        this.add(messageLabel);
        this.add(scrollpane);
        
       
        this.add(messageButton);

        // Set the action event equal to this class since it 
        // implements ActionListener
        messageButton.addActionListener(this);
        this.setSize(300, 500);
        this.setVisible(true);
    }

    /**
     * Action method for this applet.  This method will be called when this
     * class is set as an action listener.
     * 
     * @param e 
     */
    public void actionPerformed(ActionEvent e) {
        sendMail();
        this.mailLabel.setText("Message successfully sent");
        this.mailLabel.setForeground(Color.GREEN);
        repaint();
    }
    
    private boolean sendMail() {
        boolean result = false;
        try {


            // Send email here

            // servlet configuration initialization parameters

            String from = this.from.getText();
            System.out.println(from);
            String to = this.to.getText();
            String smtp = this.smtp.getText();
            String message = this.message.getText();
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", smtp);

            // create some properties and get the default Session
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(false);

            // create a message
            Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            InternetAddress[] address = new InternetAddress[1];
            address[0] = new InternetAddress(to);
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("*** Applet Email ***");
            // Append Footer
            msg.setContent(message, "text/plain");
            // Uncomment for production
            Transport.send(msg);

            result = true;
        } catch (javax.mail.MessagingException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;

    }
    
}
