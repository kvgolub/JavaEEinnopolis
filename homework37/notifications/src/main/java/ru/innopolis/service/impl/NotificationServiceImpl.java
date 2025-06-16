package ru.innopolis.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.service.NotificationService;


@Service
public class NotificationServiceImpl implements NotificationService {

    public final JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public Boolean sendNotification(EmailRequest email) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            email.getEmail().forEach(mail -> {
                        simpleMailMessage.setTo(mail);
                        simpleMailMessage.setSubject(mail.split("@")[0]);
                        simpleMailMessage.setText(email.getMessage());

                        mailSender.send(simpleMailMessage);
                    }
            );

            return  true;
        } catch (Exception e) {
            return false;
        }
    }
}
