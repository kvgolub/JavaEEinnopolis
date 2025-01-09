package ru.innopolis.model;

import lombok.*;
import ru.innopolis.Figure;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Ellipse extends Figure {
    protected Float radiusA;
    private Float radiusB;
    protected static final Float PI = 3.14f;

    @Override
    public Integer getPerimeter() {
        return Math.toIntExact(Math.round((PI * radiusA * radiusB + Math.pow(radiusA - radiusB, 2)) / radiusA + radiusB) * 2);
    }
}
