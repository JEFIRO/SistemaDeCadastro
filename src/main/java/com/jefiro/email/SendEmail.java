package com.jefiro.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public SendEmail(String user, String password) {
        this.USER = user;
        this.PASSWORD = password;
    }

    private final String USER;
    private final String PASSWORD;

    public Session iniciarSessao() {
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
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
