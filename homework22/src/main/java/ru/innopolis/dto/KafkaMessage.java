package ru.innopolis.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class KafkaMessage {

    private Long id;
    private String message;
    private LocalDateTime sendTime;

}
