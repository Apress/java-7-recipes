package org.java7recipes.chapter21;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 10/12/11
 * Time: 7:26 PM
 * Email to a group
 */
public class Recipe21_4 {
    public static void main(String[] args) {
        Recipe21_4 recipe = new Recipe21_4();
        recipe.start();
    }

    private void start() {

        List<String> emails = getEmails();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.somewhere.com");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new MessageAuthenticator("username","password"));

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("someone@somewhere.com"));
            message.setRecipients(Message.RecipientType.BCC, getRecipients(emails));
            message.setSubject("Subject");
            message.setContent("This is a test message", "text/plain");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Address[] getRecipients(List<String> emails) throws AddressException {
        Address[] addresses = new Address[emails.size()];
        for (int i =0;i < emails.size();i++) {
            addresses[i] = new InternetAddress(emails.get(i));
        }
        return addresses;
    }

    public List<String> getEmails() {
        ArrayList<String> emails = new ArrayList<>();
        emails.add("jack@hill.com");
        emails.add("jill@hill.com");
        emails.add("water@hill.com");
        return emails;
    }

    class MessageAuthenticator extends Authenticator {
        PasswordAuthentication authentication = null;

        public MessageAuthenticator(String username, String password) {
            authentication = new PasswordAuthentication(username, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
