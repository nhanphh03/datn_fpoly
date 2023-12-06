package com.example.demo.viewModel;

import com.example.demo.repository.HoaDonChiTietRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Service
public class test {


    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(String to,String subject,String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}
