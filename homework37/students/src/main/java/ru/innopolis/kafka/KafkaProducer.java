package ru.innopolis.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.mail.EmailRequest;


@Slf4j
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public Boolean sendEvent(EmailRequest email) {
        try {
            JsonSerializer<EmailRequest> jsonSerializer = new JsonSerializer<>();

            byte[] emailBytes = jsonSerializer.serialize("topic-1", email);
            jsonSerializer.close();

            kafkaTemplate.send("topic-1", emailBytes);

            log.info("Уведомление отправлено в Kafka");

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
