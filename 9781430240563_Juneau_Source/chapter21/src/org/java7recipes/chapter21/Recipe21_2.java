package org.java7recipes.chapter21;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 10/12/11
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe21_2 {

    public static void main(String[] args) {
        Recipe21_2 recipe = new Recipe21_2();
        recipe.start();
    }

    private void start() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.somewhere.com");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new MessageAuthenticator("username","password"));

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("someone@somewhere.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("someone@somewhere.com"));
            message.setSubject("Subject");
            message.setContent("This is a test message", "text/plain");
            Transport.send(message);
        } catch (MessagingException e) {
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
