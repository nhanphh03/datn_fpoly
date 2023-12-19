package com.example.demo.service.impls;

import com.example.demo.service.SendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nhanphph23607@fpt.edu.vn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    @Override
    public void sendMimeMessageKMHD(String toEmail, String subject, String body) throws MessagingException {
        String projectPath = System.getProperty("user.dir");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Chào mừng ngày hội mua sắm");
        helper.setFrom("nhanphph23607@fpt.edu.vn");
        helper.setTo(toEmail);

        String content = "<b>Xin chào Phạm Nhân</b>," +
                "<br>" +
                "<div style='height: 1200px; width: 800px; margin: auto;'>\n" +
                "    \n" +
                "        <div style='display: flex; height: 60px;'>\n" +
                "            <div style='width: 200px; display: block;'>\n" +
                "\n" +
                "                <div style='display: flex; margin: 0 0 0 10px;'>  \n" +
                "                    <a style='font-size: 11pt; font-weight: 600; margin-top: -2px; text-decoration: none; color: black;' href='tel:+038 432 9003'>\n" +
                "                        038 432 9003 \n" +
                "                    </a> \n" +
                "                </div>\n" +
                "\n" +
                "                <div style='display: flex; margin: 0 0 0 10px;'> \n" +
                "                    <a style='text-decoration: none;' href='http://localhost:8081/buyer/'>\n" +
                "                        <p style='font-size: 11pt; font-weight: 600; margin-top: -2px; text-decoration: none; color: black; '> localhost:8081/buyer/</p>\n" +
                "                    </a>  \n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "            <div style='width: 300px;'>\n" +
                "\n" +
                "            </div>\n" +
                "            <div style='width: 300px; '>\n" +
                "\n" +
                "                <img margin: 0 0 0 30px; src='cid:image007' alt=''>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div style='height: 800px; width: 800px; margin-top: 10px; border-top: 2px solid black;' > \n" +
                "            <div style='width: 800px; height: 405px;margin-top: 20px;'>\n" +
                "                <a href='##'>\n" +
                "                    <img src='cid:image001' alt=''>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style='width: 800px;'>\n" +
                "                <a href=''>\n" +
                "                    <img src='cid:image002' alt=''>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style='width: 800px; height: 415px;'>\n" +
                "                <a href='##'>\n" +
                "                    <img  style='width: 800px; height: 405px;' src='cid:image003' alt=''>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div style='margin-bottom: 40px;'>\n" +
                "                <img src='cid:image004' alt=''>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "    </div>" ;
        helper.setText(content, true);

        FileSystemResource resource1 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A1.png"));
        FileSystemResource resource2 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A2.png"));
        FileSystemResource resource3 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A3.png"));
        FileSystemResource resource4 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A4.png"));
        FileSystemResource resource7 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/Logo.png"));

        helper.addInline("image001", resource1);
        helper.addInline("image002", resource2);
        helper.addInline("image003", resource3);
        helper.addInline("image004", resource4);
        helper.addInline("image007", resource7);


        mailSender.send(message);
    }

    @Override
    public void sendMimeMessageSP(String toEmail, String subject, String linkSP, String body) throws MessagingException {
        String projectPath = System.getProperty("user.dir");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Nhân ngu");
        helper.setFrom("nhanphph23607@fpt.edu.vn");
        helper.setTo(toEmail);

        String content = "<b>Xin chào Phạm Nhân</b>," +
                "<br>" +
                "<div style='height: 1200px; width: 800px; margin: auto;'>\n" +
                "    \n" +
                "        <div style='display: flex; height: 60px;'>\n" +
                "            <div style='width: 200px; display: block;'>\n" +
                "\n" +
                "                <div style='display: flex; margin: 0 0 0 10px;'>  \n" +
                "                    <img src='cid:image005' alt=''>\n" +
                "                    <a style='font-size: 11pt; font-weight: 600; margin-top: -2px; text-decoration: none; color: black;' href='tel:+038 432 9003'>\n" +
                "                        038 432 9003 \n" +
                "                    </a> \n" +
                "                </div>\n" +
                "\n" +
                "                <div style='display: flex; margin: 0 0 0 10px;'> \n" +
                "                    <img style='height:15px; width:15px' src='cid:image006' alt=''>\n" +
                "                    <a style='text-decoration: none;'" + linkSP +"\n" +
                "                        <p style='font-size: 11pt; font-weight: 600; margin-top: -2px; text-decoration: none; color: black; '> localhost:8081/buyer/</p>\n" +
                "                    </a>  \n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "            <div style='width: 300px;'>\n" +
                "\n" +
                "            </div>\n" +
                "            <div style='width: 300px; '>\n" +
                "\n" +
                "                <img margin: 0 0 0 30px; src='cid:image007' alt=''>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div style='height: 800px; width: 800px; margin-top: 10px; border-top: 2px solid black;' > \n" +
                "            <div style='width: 800px; height: 405px;margin-top: 20px;'>\n" +
                "                <a href='##'>\n" +
                "                    <img src='cid:image001' alt=''>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style='width: 800px;'>\n" +
                "                <a href=''>\n" +
                "                    <img src='cid:image002' alt=''>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style='width: 800px; height: 415px;'>\n" +
                "                <a href='##'>\n" +
                "                    <img  style='width: 800px; height: 405px;' src='cid:image003' alt=''>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div style='margin-bottom: 40px;'>\n" +
                "                <img src='cid:image004' alt=''>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "    </div>" ;
        helper.setText(content, true);

        FileSystemResource resource1 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A1.png"));
        FileSystemResource resource2 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A2.png"));
        FileSystemResource resource3 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A3.png"));
        FileSystemResource resource4 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/A4.png"));
        FileSystemResource resource5 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/call.png"));
        FileSystemResource resource6 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/global.png"));
        FileSystemResource resource7 = new FileSystemResource(new File(projectPath+"/src/main/resources/static/images/imgEmails/Logo.png"));

        helper.addInline("image001", resource1);
        helper.addInline("image002", resource2);
        helper.addInline("image003", resource3);
        helper.addInline("image004", resource4);
        helper.addInline("image005", resource5);
        helper.addInline("image006", resource6);
        helper.addInline("image007", resource7);


        mailSender.send(message);
    }
}
