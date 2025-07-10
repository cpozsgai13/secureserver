package com.poz.secureserver.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.poz.secureserver.infrastructure.config.MailConfiguration;

@Service
public class EmailService {
    @Autowired
    private MailConfiguration mailConfiguration;

    // private JavaMailSender mailSender;

    @Value("${mail.username}")
    String sender;

    public void sendVerificationEmail(String address, String code) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(sender);
        smm.setTo(address);
        smm.setSubject("Staged - Registration");
        smm.setText("Your verification code: " + code);
        mailConfiguration.javaMailSender().send(smm);
    }

}
