package az.elvin.hotel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Utility {
    public static boolean sendMail(String to, String subject, String text) {
        boolean result = false;
        final String username = "yusiflielvin27@gmail.com";
        final String password = "elvin0123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }

                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yusiflielvin27@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("yusiflielvin27@gmail.com"));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            result = true;
            System.out.println("Mail gonderildi!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
