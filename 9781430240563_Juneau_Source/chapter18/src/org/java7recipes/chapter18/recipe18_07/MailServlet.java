package org.java7recipes.chapter18.recipe18_07;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Recipe 18-7
 * 
 * Setting application-wide parameters.
 * 
 * @author juneau
 */
public class MailServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        String email = req.getParameter("email");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String feedback = req.getParameter("feedback");

        PrintWriter out = res.getWriter();

        /* Display some response to the user */
        out.println("<html><head>");
        out.println("<title>Company Feedback</title>");
        out.println("\t<style>body { font-family: 'Lucida Grande', "
                + "'Lucida Sans Unicode';font-size: 13px; }</style>");
        out.println("</head>");
        out.println("<body>");
        if (sendMail(email, fname, lname, feedback)) {
            out.println("<p>Email sent, expect a response soon!</p>");
        } else {
            out.println("<p>There was an issue with the email, please try again.</p>");
        }
        out.println("</body></html>");

        out.close();
    }

    private boolean sendMail(String email, String fname, String lname, String feedback) {
        boolean result = false;
        try {


            // Send email here

            // servlet configuration initialization parameters

            String contextEmail =
                    getServletConfig().getInitParameter("emailAddress");
            String contextSmtp =
                    getServletConfig().getInitParameter("smtpAddress");
            //Set the host smtp address
            Properties props = new Properties();
            //String smtpServer = obtainProperties("SMTP_SERVER");
            props.put("mail.smtp.host", contextSmtp);

            // create some properties and get the default Session
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(false);

            // create a message
            Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(email);
            msg.setFrom(addressFrom);
            InternetAddress[] address = new InternetAddress[1];
            address[0] = new InternetAddress(email);
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("***Customer Feedback ***");
            // Append Footer
            msg.setContent(feedback, "text/plain");
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
