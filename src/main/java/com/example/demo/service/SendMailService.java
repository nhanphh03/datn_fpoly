package com.example.demo.service;

import jakarta.mail.MessagingException;

public interface SendMailService {

    void sendSimpleMail(String toEmail,
                        String subject,
                        String body);


    void sendMimeMessageKMHD(String toEmail,
                        String subject,
                        String body) throws MessagingException;

    void sendMimeMessageSP(String toEmail,
                             String subject,
                             String linkSP,
                             String body) throws MessagingException;
}
