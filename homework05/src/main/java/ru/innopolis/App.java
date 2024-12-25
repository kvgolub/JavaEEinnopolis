package ru.innopolis;

import ru.innopolis.model.Circle;
import ru.innopolis.model.Ellipse;
import ru.innopolis.model.Rectangle;
import ru.innopolis.model.Square;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        List<String> figuresFromFile = new ArrayList<>();
        List<String> resultToFile = new ArrayList<>();

        //Чтение данных о фигурах из файла
        try(BufferedReader br = new BufferedReader(new FileReader("/home/figures/figures.txt"))) {
            String oneFigure;
            while ((oneFigure = br.readLine()) != null) {
                figuresFromFile.add(oneFigure);
            }


            //Прямоугольник
            String[] rectangleData = figuresFromFile.get(0).split(",");
            Rectangle rectangle = new Rectangle();
            rectangle.setSideA(Integer.parseInt(rectangleData[1]));
            rectangle.setSideB(Integer.parseInt(rectangleData[2]));
            resultToFile.add("Square side A = " + rectangle.getSideA() + "\n");
            resultToFile.add("Square Side B = " + rectangle.getSideB() + "\n");
            resultToFile.add("Square perimeter = " + rectangle.getPerimeter() + "\n\n");


            //Эллипс
            String[] ellipseData = figuresFromFile.get(1).split(",");
            Ellipse ellipse = new Ellipse();
            ellipse.setRadiusA(Float.parseFloat(ellipseData[1]));
            ellipse.setRadiusB(Float.parseFloat(ellipseData[2]));
            resultToFile.add("Ellipse Radius A = " + ellipse.getRadiusA() + "\n");
            resultToFile.add("Ellipse Radius B = " + ellipse.getRadiusB() + "\n");
            resultToFile.add("Ellipse perimeter = " + ellipse.getPerimeter() + "\n\n");


            //Квадрат
            String[] squareData = figuresFromFile.get(2).split(",");
            Square square = new Square();
            square.setSideA(Integer.parseInt(squareData[1]));
            resultToFile.add("Square Side A = " + square.getSideA() + "\n");
            resultToFile.add("Square perimeter = " + square.getPerimeter() + "\n");

            resultToFile.add("Square: start coordinate X = " + square.getCoordinateX() + ", " + "start coordinate Y = " + square.getCoordinateY() + "\n");
            square.move(Integer.parseInt(squareData[3]), Integer.parseInt(squareData[4]));
            resultToFile.add("Square: new coordinate X = " + square.getCoordinateX() + ", " + "new coordinate Y = " + square.getCoordinateY() + "\n\n");


            //Окружность
            String[] circleData = figuresFromFile.get(3).split(",");
            Circle circle= new Circle();
            circle.setRadiusA(Float.parseFloat(circleData[1]));
            resultToFile.add("Circle Radius A = " + circle.getRadiusA() + "\n");
            resultToFile.add("Circle perimeter = " + circle.getPerimeter() + "\n");

            resultToFile.add("Circle: start coordinate X = " + circle.getCoordinateX() + ", " + "start coordinate Y = " + circle.getCoordinateY() + "\n");
            circle.move(Integer.parseInt(circleData[3]), Integer.parseInt(circleData[4]));
            resultToFile.add("Circle: new coordinate X = " + circle.getCoordinateX() + ", " + "new coordinate Y = " + circle.getCoordinateY() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Запись вычислений в файл
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("/home/figures/result.txt"))) {
            for(String a : resultToFile) {
                bw.write(a);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
