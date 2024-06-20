package com.jefiro.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    private static final String USER = System.getenv("userGmail");
    private static final String PASSWORD = System.getenv("passGmail");

    public Session iniciarSessao() {
        try {
            System.out.println(USER);
            System.out.println(PASSWORD);
            String host = "smtp.gmail.com";
            String port = "587";

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            return Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER, PASSWORD);
                }
            });

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Message criarMenssagem(Session session, String email, String assunto, String corpo) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(assunto);
            message.setText(corpo);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    public void enviarMenssagem(Message message) {
        try {
            Transport.send(message);
        } catch (MessagingException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
