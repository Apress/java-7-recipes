
package org.java7recipes.chapter18.recipe18_17;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Recipe 18-17
 * 
 * Loading external libraries for an applet.
 * 
 * @author juneau
 */
public class MailApplet extends Applet implements ActionListener {

    TextField from;
    TextField to;
    TextField smtp;
    TextArea message;
    Label mailLabel;
    Label fromLabel;
    Label toLabel;
    Label smtpLabel;
    Label blank;
    Label messageLabel;
    Button messageButton;

    public void init() {

        // Construct the button
        
        messageButton = new Button("Send");

        mailLabel = new Label("Please fill out the form below to send email.");
        fromLabel = new Label("From:");
        from = new TextField();
        toLabel = new Label("To:");
        to = new TextField();
        smtpLabel = new Label("SMTP Host:");
        smtp = new TextField();
        messageLabel = new Label("Message:");
        message = new TextArea(null,10,30);
        blank = new Label();

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
        this.add(message);
        
       
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
