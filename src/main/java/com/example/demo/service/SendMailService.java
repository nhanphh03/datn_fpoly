package com.example.demo.service;

public interface SendMailService {

    void sendSimpleMail(String toEmail,
                        String subject,
                        String body);
}
