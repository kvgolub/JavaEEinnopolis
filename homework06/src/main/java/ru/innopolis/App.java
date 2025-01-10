package ru.innopolis;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class App {
    public static void main(String[] args) {
        try {
            //Считываем данные из файла
            Object writeFile = new JSONParser().parse(new FileReader("/home/collections/src/main/resources/collections.json"));
            JSONObject generateObj = (JSONObject) writeFile;

            //Получаем информацию о List
            ArrayList<Long> listWithNumber = (JSONArray) generateObj.get("List");

            //Получаем информацию о HashSet
            ArrayList<String> setWithNumber = (JSONArray) generateObj.get("HashSet");

            //Создаем JSON-объекты для сохранения результата
            JSONObject forList = new JSONObject();
            JSONObject forHashSet = new JSONObject();
            JSONObject result = new JSONObject();


        //List
            System.out.println("List:");

            //a) Создайте массив из N чисел
            Integer[] startList = new Integer[listWithNumber.size()];
            for (int i = 0; i < listWithNumber.size(); i++) {
                startList[i] = listWithNumber.get(i).intValue();
            }
            System.out.println("a) Создан массив из 7 чисел: " + Arrays.stream(startList).toList());
            forList.put("a) Создайте массив из N чисел", Arrays.stream(startList).toList().toString());


            //b) На основе массива создайте список List
            List<Integer> numbers = new ArrayList<>(List.of(startList));
            System.out.println("b) Создан список: " + numbers);
            forList.put("b) На основе массива создайте список List", numbers.toString());


            //c) Отсортируйте список в натуральном порядке
            Collections.sort(numbers);
            System.out.println("c) Список отсортирован в прямом порядке: " + numbers);
            forList.put("c) Отсортируйте список в натуральном порядке", numbers.toString());


            //d) Отсортируйте список в обратном порядке
            Collections.reverse(numbers);
            System.out.println("d) Список отсортирован в обратном порядке: " + numbers);
            forList.put("d) Отсортируйте список в обратном порядке", numbers.toString());


            //e) Перемешайте список
            Collections.shuffle(numbers);
            System.out.println("e) Список перемешан: " + numbers);
            forList.put("e) Перемешайте список", numbers.toString());


            //f) Выполните циклический сдвиг на 1 элемент
            Collections.rotate(numbers, -1);
            System.out.println("f) Выполнен циклический сдвиг: " + numbers);
            forList.put("f) Выполните циклический сдвиг на 1 элемент", numbers.toString());


            //g) Оставьте в списке только уникальные элементы
            List<Integer> unique = new ArrayList<>();
            numbers.forEach(n -> {
                        if(Collections.frequency(numbers, n) == 1) {
                            unique.add(n);
                        }
                    }
            );
            System.out.println("g) Список с уникальными значениями: " + unique);
            forList.put("g) Оставьте в списке только уникальные элементы", unique.toString());


            //h) Оставьте в списке только дублирующиеся элементы
            List<Integer> duplicate = new ArrayList<>();
            numbers.forEach(n -> {
                        if(Collections.frequency(numbers, n) >= 2) {
                            duplicate.add(n);
                        }
                    }
            );
            System.out.println("h) Список с повторяющимися значениями: " + duplicate);
            forList.put("h) Оставьте в списке только дублирующиеся элементы", duplicate.toString());


            //i) Из списка получите массив
            Integer[] numbersNew = numbers.toArray(new Integer[0]);
            System.out.println("i) Из списка получен массив: " + Arrays.stream(numbersNew).toList());
            forList.put("i) Из списка получите массив", Arrays.stream(numbersNew).toList().toString());



        //HashSet
            System.out.println("\nHashSet:");

            //a) Создать коллекцию HashSet с типом элементов String. Добавить в неё 10 строк
            Set<String> words = new HashSet<>(setWithNumber);
            System.out.println("a) Создана коллекция HashSet: " + words);
            forHashSet.put("a) Создать коллекцию HashSet с типом элементов String. Добавить в неё 10 строк", words.toString());


            //b) Добавить в множество минимум пять объектов, которые являются совместимыми с типом данных коллекции
            Collections.addAll(words, "отвертка", "пила", "гаечный ключ", "саморез", "кусачки");
            //Альтернативный вариант: words.addAll(Set.of("отвертка", "пила", "гаечный ключ", "саморез", "кусачки"));
            System.out.println("b) Добавлено 5 новых элементов: " + words);
            forHashSet.put("b) Добавить в множество минимум пять объектов, которые являются совместимыми с типом данных коллекции", words.toString());


            //c) Вывести на экран элементы множества используя цикл for.
            for (String element : words) {
                System.out.print(element + "  ");
            }


            //d) Добавить новый элемент, который уже присутствует в множестве
            words.add("пила");
            System.out.println("\nd) Добавлен существующий элемент 'пила': " + words);
            forHashSet.put("d) Добавить новый элемент, который уже присутствует в множестве", words.toString());


            //e) Определить, содержит ли коллекция определенный объект
            //System.out.println(rrr.stream().anyMatch(r -> r.equals("саморез")));
            System.out.println("e) Проверка наличия элемента 'саморез': " + Collections.frequency(words, "саморез"));
            forHashSet.put("e) Определить, содержит ли коллекция определенный объект", words.toString());


            //f) Удалить из коллекции любой объект
            words.remove("гаечный ключ");
            System.out.println("f) Из коллекции удален 'гаечный ключ': " + words);
            forHashSet.put("f) Удалить из коллекции любой объект", words.toString());


            //g) Получить количество элементов, содержащихся в коллекции на данный момент
            System.out.println("g) Количество элементов: " + words.size());
            forHashSet.put("g) Получить количество элементов, содержащихся в коллекции на данный момент", words.toString());


            //h) Удалить все элементы множества
            words.clear();
            System.out.println("h) Все элементы удалены: " + words);
            forHashSet.put("h) Удалить все элементы множества", words.toString());


            //i) Проверить, является ли коллекция HashSet пустой
            System.out.println("i) Проверка на пустоту: " + words.isEmpty());
            forHashSet.put("i) Проверить, является ли коллекция HashSet пустой", words.toString());


            //Добавление коллекций в результирующий объект
            result.put("List", forList);
            result.put("HashSet", forHashSet);


            //Сохранение результата в файл
            FileWriter saveResult = new FileWriter(new File("/home/collections/result.json"));
            result.writeJSONString(saveResult);
            saveResult.close();

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
