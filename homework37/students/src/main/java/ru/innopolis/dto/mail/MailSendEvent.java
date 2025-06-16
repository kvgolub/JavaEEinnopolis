package ru.innopolis.dto.mail;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailSendEvent {

    private Long countOfNotifications;
}
