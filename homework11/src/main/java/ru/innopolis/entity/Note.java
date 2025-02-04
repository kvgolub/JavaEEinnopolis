package ru.innopolis.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Note {

    private Integer id;
    private String noteDate;
    private String theme;
    private String textnote;

}
