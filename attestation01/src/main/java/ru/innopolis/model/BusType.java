package ru.innopolis.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BusType {
    private Integer id;
    private String nameType;
}
