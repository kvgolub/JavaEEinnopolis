package ru.innopolis.kafka.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.kafka.KafkaProducer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @InjectMocks
    private KafkaProducer kafkaProducer;


    @Test
    void sendEventKafkaTest() {
        EmailRequest emailRequest = new EmailRequest(
              List.of("ivanov@mail.ru", "sidorov@mail.ru"),
                "Старт обучения по программе Java - 01.06.2025 в 12:00"
        );

        var response = kafkaProducer.sendEvent(emailRequest);

        Assertions.assertTrue(response);
    }
}