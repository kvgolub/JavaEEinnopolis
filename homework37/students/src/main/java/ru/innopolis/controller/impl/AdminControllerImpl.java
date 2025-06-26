package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.AdminController;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.kafka.KafkaProducer;


@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final KafkaProducer kafkaProducer;


    @Override
    public ResponseEntity<Boolean> sendEvent(EmailRequest email) {
        Boolean completeNotification = kafkaProducer.sendEvent(email);

        if (completeNotification) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
