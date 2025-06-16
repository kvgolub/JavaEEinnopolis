package ru.innopolis.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.innopolis.controller.NotificationController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.service.NotificationService;


@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;


    @Override
    public ResponseEntity<Boolean> sendNotification(EmailRequest email) {
        Boolean completeNotification = notificationService.sendNotification(email);

        if (completeNotification) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
