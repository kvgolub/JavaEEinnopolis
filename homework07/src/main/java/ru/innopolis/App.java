package ru.innopolis;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class App {
    public static void main(String[] args) {
        try {
            //Считываем данные из файла
            Object writeFile = new JSONParser().parse(new FileReader("/home/collections/src/main/resources/collections.json"));
            JSONObject generateObj = (JSONObject) writeFile;

            //Создаем JSON-объекты для сохранения результата
            JSONObject forHashMap = new JSONObject();
            JSONObject result = new JSONObject();

        //HashMap
            System.out.println("HashMap:");


            //a) Создать коллекцию HashMap с типом элементов String. Добавить в неё 10 строк (предпочтительно чтение из файла)
            HashMap<String, String> plants = new HashMap<>();
            generateObj.forEach((keyMap, valueMap) -> {
                plants.put(keyMap.toString(), valueMap.toString());
            });
            System.out.println("a) Создать коллекцию HashMap с типом элементов String. Добавить в неё 10 строк (предпочтительно чтение из файла): " + plants);
            forHashMap.put("a) Создать коллекцию HashMap с типом элементов String. Добавить в неё 10 строк (предпочтительно чтение из файла)", plants.toString());


            //b) Добавить в коллекцию минимум пять пар «ключ-значение». Несколько ключей должны иметь одно и тоже значение
            plants.put("fruit_4", "Яблоко");
            plants.put("vegetable_4", "Помидор");
            plants.put("flower_1", "Ромашка");
            plants.put("flower_2", "Роза");
            plants.put("flower_3", "Василёк");
            System.out.println("b) Добавить в коллекцию минимум пять пар «ключ-значение». Несколько ключей должны иметь одно и тоже значение (\"fruit_1\" и \"fruit_4\" содержат одинаковое значение \"Яблоко\", \"vegetable_3\" и \"vegetable_4\" содержат одинаковое значение \"Помидор\"): " + plants);
            forHashMap.put("b) Добавить в коллекцию минимум пять пар «ключ-значение». Несколько ключей должны иметь одно и тоже значение ('fruit_1' и 'fruit_4' содержат одинаковое значение 'Яблоко', 'vegetable_3' и 'vegetable_4' содержат одинаковое значение 'Помидор')", plants.toString());


            //c) Выполнить прямой перебор коллекции используя цикл for
            System.out.println("c) Выполнить прямой перебор коллекции используя цикл for: ");
            for (Object entry : plants.entrySet()) {
                System.out.println(entry);
            }


            //d) Добавить новый элемент с уже имеющимся в отображении ключом
            plants.put("berry_3", "Черная смородина");
            System.out.println("d) Добавить новый элемент с уже имеющимся в отображении ключом \"berry_3\": " + plants.get("berry_3"));
            forHashMap.put("d) Добавить новый элемент с уже имеющимся в отображении ключом 'berry_3'", plants.get("berry_3").toString());


            //e) Вынести список всех ключей из HashMap в отдельную коллекцию
            Set<String> keySet = new HashSet<>(plants.keySet());
            System.out.println("e) Вынести список всех ключей из HashMap в отдельную коллекцию: " + keySet);
            forHashMap.put("e) Вынести список всех ключей из HashMap в отдельную коллекцию", keySet.toString());


            //f) Вынести список всех значений из HashMap в коллекцию HashSet и получить количество уникальных значений
            Set<String> valueSet = new HashSet<>(plants.values());
            System.out.println("f) Вынести список всех значений из HashMap в коллекцию HashSet и получить количество уникальных значений: " + valueSet);
            forHashMap.put("f) Вынести список всех значений из HashMap в коллекцию HashSet и получить количество уникальных значений", valueSet.toString());
            //количество уникальных значений
            System.out.println("f) Количество уникальных значений (\"fruit_1\" и \"fruit_4\" содержат одинаковое значение \"Яблоко\", \"vegetable_3\" и \"vegetable_4\" содержат одинаковое значение \"Помидор\"): " + valueSet.size());
            forHashMap.put("f) Количество уникальных значений ('fruit_1' и 'fruit_4' содержат одинаковое значение 'Яблоко', 'vegetable_3' и 'vegetable_4' содержат одинаковое значение 'Помидор')", valueSet.size());


            //g) Определить, содержит ли коллекция HashMap определенный ключ
            System.out.println("g) Определить, содержит ли коллекция HashMap определенный ключ \"vegetable_1\": " + plants.containsKey("vegetable_1"));
            System.out.println("g) Определить, содержит ли коллекция HashMap определенный ключ \"berry_5\": " + plants.containsKey("berry_5"));
            forHashMap.put("g) Определить, содержит ли коллекция HashMap определенный ключ 'vegetable_1'", plants.containsKey("vegetable_1"));
            forHashMap.put("g) Определить, содержит ли коллекция HashMap определенный ключ 'berry_5'", plants.containsKey("berry_5"));


            //h) Определить, содержит ли коллекция HashMap определенное значение
            System.out.println("h) Определить, содержит ли коллекция HashMap определенное значение \"Редис\": " + plants.containsValue("Редис"));
            System.out.println("h) Определить, содержит ли коллекция HashMap определенное значение \"Крыжовник\": " + plants.containsValue("Крыжовник"));
            forHashMap.put("h) Определить, содержит ли коллекция HashMap определенное значение 'Редис'", plants.containsValue("Редис"));
            forHashMap.put("h) Определить, содержит ли коллекция HashMap определенное значение 'Крыжовник'", plants.containsValue("Крыжовник"));


            //i) Получить количество элементов, содержащихся в данный момент в коллекции HashMap
            System.out.println("i) Получить количество элементов, содержащихся в данный момент в коллекции HashMap: " + plants.size());
            forHashMap.put("i) Получить количество элементов, содержащихся в данный момент в коллекции HashMap", plants.size());


            //j) Удалить из коллекции выбранный объект по ключу и по значению
            System.out.println("j) Удалить из коллекции выбранный объект по ключу \"berry_1\" и по значению \"Ежевика\": " + plants.remove("berry_1", "Ежевика"));
            //System.out.println("j) Удалить из коллекции выбранный объект по ключу \"berry_1\" и по значению \"Малина\": " + plants.remove("berry_1", "Малина"));
            forHashMap.put("j) Удалить из коллекции выбранный объект по ключу 'berry_1' и по значению 'Ежевика'", plants.remove("berry_1", "Ежевика"));
            forHashMap.put("j) Удалить из коллекции выбранный объект по ключу 'berry_1' и по значению 'Малина'", plants.remove("berry_1", "Малина"));


            //Добавление коллекций в результирующий объект
            result.put("HashMap", forHashMap);


            //Сохранение результата в файл
            FileWriter saveResult = new FileWriter(new File("/home/collections/result.json"));
            result.writeJSONString(saveResult);
            saveResult.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
