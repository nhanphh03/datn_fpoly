package com.example.demo.service;

import jakarta.mail.MessagingException;

public interface SendMailService {

    void sendSimpleMail(String toEmail,
                        String subject,
                        String body);


    void sendMimeMessage(String toEmail,
                        String subject,
                        String body) throws MessagingException;
}
