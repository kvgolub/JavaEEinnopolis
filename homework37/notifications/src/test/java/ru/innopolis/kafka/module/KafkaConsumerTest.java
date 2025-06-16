package ru.innopolis.kafka.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.kafka.KafkaConsumer;
import ru.innopolis.service.NotificationService;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class KafkaConsumerTest {

    @Mock
    public NotificationService notificationService;

    @InjectMocks
    private KafkaConsumer kafkaConsumer;


    @Test
    void listenKafkaTest() {
        Mockito.when(notificationService.sendNotification(Mockito.any(EmailRequest.class))).thenReturn(true);

        EmailRequest emailRequest = new EmailRequest(
                List.of("ivanov@mail.ru", "sidorov@mail.ru"),
                "Старт обучения по программе Java - 01.06.2025 в 12:00"
        );

        var response = kafkaConsumer.listen(serializer(emailRequest));

        Assertions.assertTrue(response);
    }


    private byte[] serializer(EmailRequest email) {
        JsonSerializer<EmailRequest> jsonSerializer = new JsonSerializer<>();

        byte[] emailBytes = jsonSerializer.serialize("topic-1", email);
        jsonSerializer.close();

        return emailBytes;
    }
}
