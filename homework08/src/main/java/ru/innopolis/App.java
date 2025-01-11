package ru.innopolis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


public class App {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

//        System.out.println("Длина строки 'Муха-цокотуха': " + safeStringLength.apply("Муха-цокотуха"));
//        System.out.println("Пустая строка: " + safeStringLength.apply(""));

        List<String> stringsFromFile = new ArrayList<>();
        List<String> resultToFile = new ArrayList<>();

        //Чтение данных о строках из файла
        try(BufferedReader br = new BufferedReader(new FileReader("/home/strings/src/main/resources/string.txt"))) {
            String oneString;
            while ((oneString = br.readLine()) != null) {
                stringsFromFile.add(oneString);
            }

            //Вычисление строки "Муха-цокотуха"
            Integer noEmptyStr = safeStringLength.apply(stringsFromFile.get(0).split(":")[1]);
            resultToFile.add("Длина строки 'Муха-цокотуха': " + noEmptyStr);


            //Вычисление пустой строки
            Integer emptyStr = safeStringLength.apply(stringsFromFile.get(1).split(":", 2)[1]);
            resultToFile.add("\nПустая строка: " + emptyStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //Запись результат в файл
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("/home/strings/result.txt"))) {
            for(String a : resultToFile) {
                bw.write(a);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Function<String, Integer> ternaryOperator(Predicate<Object> condition, Function<Object, Integer> ifTrue, Function<CharSequence, Integer> ifFalse) {
        return x -> condition.test(x) ? ifTrue.apply(x) : ifFalse.apply(x);
    }
}
