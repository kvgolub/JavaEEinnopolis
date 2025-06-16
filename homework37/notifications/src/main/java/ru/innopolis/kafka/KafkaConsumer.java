package ru.innopolis.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.service.NotificationService;


@Slf4j
@Component
public class KafkaConsumer {

    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "topic-1", groupId = "group-1"/*, clientIdPrefix = "consumer-group-1-1"*/)
    public Boolean listen(byte[] email) {
        try {
            log.info("Уведомление получено из Kafka");

            JsonDeserializer<EmailRequest> jsonSerializer = new JsonDeserializer<>(EmailRequest.class);

            log.info("Уведомление направлено на почту");

            EmailRequest emailRequest = jsonSerializer.deserialize("topic-1", email);
            jsonSerializer.close();

            Boolean sendNotif = notificationService.sendNotification(emailRequest);

            log.info("Отправка уведомления на почту выполнена = {}", sendNotif);

            return sendNotif;
        } catch (Exception e) {
            return false;
        }
    }
}
