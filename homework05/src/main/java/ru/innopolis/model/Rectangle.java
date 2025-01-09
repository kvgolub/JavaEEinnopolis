package ru.innopolis.model;

import lombok.*;
import ru.innopolis.Figure;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Rectangle extends Figure {
    protected Integer sideA;
    private Integer sideB;

    @Override
    public Integer getPerimeter() {
        return (sideA + sideB) * 2;
    }
}
