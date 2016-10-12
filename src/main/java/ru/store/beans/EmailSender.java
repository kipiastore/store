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

    public EmailSender() {}

    public void send(String name, String email, String body, int type) {

    }
}
