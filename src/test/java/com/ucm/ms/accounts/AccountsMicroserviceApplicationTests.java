package com.ucm.ms.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
@AutoConfigureTestDatabase
class AccountsMicroserviceApplicationTests {

    @Autowired
    private JavaMailSender emailSender;

    @Test
    void contextLoads() {
    }

    @Test
    void emailTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@cashmoney.com");
        message.setTo("user@email.com");
        message.setSubject("Test Email");
        message.setText("This is a test email message.");
        emailSender.send(message);
    }

}
