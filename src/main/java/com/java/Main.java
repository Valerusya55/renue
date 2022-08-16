package com.java;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку для поиска:");
        String data = in.nextLine();
        String line;
        String searchLine;
        Map<String, String> result = new TreeMap<>();
        while (!data.equals("!quit")) {
            result.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/src/airports.csv"))) {
                long m = System.currentTimeMillis();
                while ((line = reader.readLine()) != null) {
                    String[] cols = line.split(",");
                    searchLine = cols[Integer.parseInt(args[0].trim()) - 1].replaceAll("^\"|\"$", "");
                    if (searchLine.regionMatches(true, 0, data, 0, data.length())) {
                        result.put(searchLine, line);
                    }
                }
                for (Map.Entry<String, String> pair : result.entrySet()) {
                    System.out.println(pair.getKey() + "[" + pair.getValue() + "]");
                }
                System.out.println("Строк:" + result.size());
                System.out.println("Время поиска:" + (double) (System.currentTimeMillis() - m) + " мс");
                System.out.println("Введите строку для поиска:");
                data = in.nextLine();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Не правильно задан параметр");
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
