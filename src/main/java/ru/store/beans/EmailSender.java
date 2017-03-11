package ru.store.beans;

import org.springframework.stereotype.Component;
import ru.store.exceptions.NotFoundException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 */
@Component
public class EmailSender {

    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final int INVITE_EMPLOYEE = 1;
    private static final int INVITE_CUSTOMER = 2;
    private static final int SUPPORT_SERVICE = 3;
    private Properties javaxMailProperties;
    private String technicalSupport;
    private String username;
    private String password;

    public EmailSender() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream;
        String errorMessage = "";
        try {
            errorMessage = "javaxMailSettings.properties not found!";
            inputStream = classLoader.getResourceAsStream("config/javaxMailSettings.properties");
            javaxMailProperties = new Properties();
            javaxMailProperties.load(inputStream);
            errorMessage = "baseSettings.properties not found!";
            inputStream = classLoader.getResourceAsStream("config/baseSettings.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            username = properties.getProperty("GoogleUserName");
            password = properties.getProperty("GooglePassword");
            technicalSupport = properties.getProperty("PortalTechnicalSupport");
        } catch (Exception ex) {
            throw new NotFoundException(errorMessage);
        }
    }

    public void send(String name, String email, String body, int type) {
        Session session = Session.getInstance(javaxMailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            InternetAddress fromAddress = new InternetAddress();
            fromAddress.setAddress(email);
            fromAddress.setPersonal(name);
            InternetAddress replyAddress = new InternetAddress();
            replyAddress.setAddress(email);
            replyAddress.setPersonal(name);
            String subject = "";
            if (type == INVITE_EMPLOYEE) {
                subject = "Заявка на вакансию";
            }
            if (type == INVITE_CUSTOMER) {
                subject = "Заявка на площадку";
            }
            if (type == SUPPORT_SERVICE) {
                subject = "Отзыв";
            }
            Message message = new MimeMessage(session);
            message.setFrom(fromAddress);
            message.setReplyTo(new Address[] { replyAddress });
            message.setSubject(subject);
            message.setText(body);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
            if (type == SUPPORT_SERVICE) {
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(technicalSupport));
            }
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
