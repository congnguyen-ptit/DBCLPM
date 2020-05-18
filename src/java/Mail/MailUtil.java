/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author CongNguyen
 */
public class MailUtil {
    public static void sendMail(String[] recepient, String subject, String content) throws MessagingException {
        System.out.println("Preparing to send mail");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        String username = "congnx1998215@gmail.com";
        String password = "Congpro98";
        Authenticator auth = new Authenticator() {	
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
            }
	};
        Session session = Session.getInstance(properties, auth);
        Message message = preparedMessage(session, username, recepient, subject, content);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message preparedMessage(Session session, String myEmail, String[] recepient, String subject, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            InternetAddress[] address = new InternetAddress[recepient.length];
            for(int i = 0;i< recepient.length;i++) {
                address[i] = new InternetAddress(recepient[i]);
            }
            message.addRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setText(content);
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
