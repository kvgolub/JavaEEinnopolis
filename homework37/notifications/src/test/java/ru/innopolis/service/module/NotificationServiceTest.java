package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.service.impl.NotificationServiceImpl;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    public JavaMailSender mailSender;

    @InjectMocks
    private NotificationServiceImpl notificationService;


    @Test
    void sendNotificationServiceTest() {
        EmailRequest emailRequest = new EmailRequest(
                List.of("ivanov@mail.ru", "sidorov@mail.ru"),
                "Старт обучения по программе Java - 01.06.2025 в 12:00"
        );

        var response = notificationService.sendNotification(emailRequest);

        Assertions.assertTrue(response);
    }
}
