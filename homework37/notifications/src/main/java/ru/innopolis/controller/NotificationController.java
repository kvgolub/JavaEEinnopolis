package ru.innopolis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.mail.EmailRequest;


@RequestMapping("/api/v1/notification")
public interface NotificationController {

    @PostMapping
    ResponseEntity<Boolean> sendNotification(@RequestBody EmailRequest email);
}
