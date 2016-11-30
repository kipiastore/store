package ru.store.beans;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class EmailSender {

    public static final int INVITE_EMPLOYEE = 1;
    public static final int INVITE_CUSTOMER = 2;
    public static final int SUPPORT_SERVUCE = 3;

    public static final String EMAIL_REGX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public EmailSender() {}

    public void send(String name, String email, String body, int type) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(body);
        System.out.println(type);
    }
}
