package com.ge.tms.common.mail;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Nitin K.
 * Class containing method to send an email.
 */
public interface MailSender {

    /**
     * Method to send an email.
     * @param to String
     * @param subject String
     * @param body String
     * @return void
     * @author Nitin K.
     */
    @Async
    void send(String to, String subject, String body);
}
