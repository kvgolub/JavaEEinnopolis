package ru.innopolis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.mail.EmailRequest;


@RequestMapping("/api/v1/admin")
public interface AdminController {

    @PostMapping ("/kafka")
    ResponseEntity<Boolean> sendEvent(@RequestBody EmailRequest email);
}
