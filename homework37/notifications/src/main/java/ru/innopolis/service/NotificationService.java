package ru.innopolis.service;

import ru.innopolis.dto.mail.EmailRequest;

public interface NotificationService {

    Boolean sendNotification(EmailRequest email);
}
