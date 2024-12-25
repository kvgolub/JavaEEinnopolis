package ru.innopolis.model;

import lombok.*;
import ru.innopolis.MoveFigure;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Circle extends Ellipse implements MoveFigure {
    private Integer coordinateX = 0;
    private Integer coordinateY = 0;

    @Override
    public void move(Integer x, Integer y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }

    @Override
    public Integer getPerimeter() {
        return Math.toIntExact(Math.round(2 * PI * radiusA));
    }
}
