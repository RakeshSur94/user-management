package com.um.service.portal.utils.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendEmailVerificationCode {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;


    public void sendOtpEmail(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Password");
        message.setText("Dear User,\n\nYour OTP code is: " + password +
        "\n\nPlease use this code to complete your authentication process.\n\n" +
                "You can also use the following link to update your information:\n" +
                "\n\nBest regards,\nRakesh Sur Team");
        log.info("Sending email to: {}", email);
        mailSender.send(message);
    }


}
