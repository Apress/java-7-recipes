package org.java7recipes.chapter21;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 10/12/11
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe21_3 {

    public static void main(String[] args) {
        Recipe21_3 recipe = new Recipe21_3();
        recipe.start();
    }

    private void start() {
        String host = "smtp.somewhere.com";
        String username = "username";
        String password = "password";
        String from = "someone@somewhere.com";
        String to = "anotherone@somewhere.com";



        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new MessageAuthenticator(username,password));

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Subject");

            // Create Mime Content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("This is a test message", "text/plain");

            MimeBodyPart fileBodyPart = new MimeBodyPart();
            fileBodyPart.attachFile("attach.txt");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(fileBodyPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    class MessageAuthenticator extends Authenticator {
        PasswordAuthentication authentication = null;

        public MessageAuthenticator(String username, String password) {
            authentication = new PasswordAuthentication(username,password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
