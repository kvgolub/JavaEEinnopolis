package ru.innopolis.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BusStop {
    private Integer id;
    private String nameStop;
}
