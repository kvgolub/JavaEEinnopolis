package ru.innopolis.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.innopolis.dto.KafkaMessage;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "message", groupId = "group_id")
    public void listen(KafkaMessage message) {
        log.info("message: {}", message);
    }

}
