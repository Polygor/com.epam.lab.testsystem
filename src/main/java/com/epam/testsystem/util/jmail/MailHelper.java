package com.epam.testsystem.util.jmail;

import com.epam.testsystem.model.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class MailHelper {

    public static final String UTF_8 = "utf-8";
    public static final String HTML = "html";
    public static final String SYSTEM_MAIL = "systemfortesting2015@gmail.com";
    public static final String PLAIN = "plain";
    public static final String A_HREF = "<a href='http://localhost:8080/testsystem/login.do'>";
//JBOSS WILDFLY MAIL CONFIG
    public static void sendEmail(Session session, User user, String userPassword,
                                 String locale) throws MessagingException, IOException {
        SmtpMessageSender messageSender = new SmtpMessageSender();
        MimeMessage message;
        if (locale.equals("ru")) {
            message = messageSender.createMimeMessage(session,
                    "Привет, " + user.getUsername(),
                    SYSTEM_MAIL,
                    user.getEmail(),
                    Message.RecipientType.TO);
            messageSender.addText(message, "Ваш логин: " + user.getUsername(), UTF_8, PLAIN);
            messageSender.addText(message, "Ваш пароль: " + userPassword, UTF_8, PLAIN);
            messageSender.addText(message,
                    A_HREF + "Перейти на стартовую страницу</a>",
                    UTF_8,
                    HTML);
        } else {
            message = messageSender.createMimeMessage(session,
                    "Hello, " + user.getUsername(),
                    SYSTEM_MAIL,
                    user.getEmail(),
                    Message.RecipientType.TO);
            messageSender.addText(message, "Your username: " + user.getUsername(), UTF_8, PLAIN);
            messageSender.addText(message, "Your password: " + userPassword, UTF_8, PLAIN);
            messageSender.addText(message,
                    A_HREF + "Go to main page</a>",
                    UTF_8,
                    HTML);
        }
        messageSender.sendMimeMessage(message);
    }
}
