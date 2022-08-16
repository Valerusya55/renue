package com.java;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String data = null;
        String line;
        String searchLine;
        int count;
        Map<String, String> result = new TreeMap<>();
        while (!Objects.equals(data, "!quit")) {
            result.clear();
            count = 0;
            System.out.println("Введите строку для поиска:");
            data = in.nextLine();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/src/airports.csv"))) {
                long m = System.currentTimeMillis();
                while ((line = reader.readLine()) != null) {
                    String[] cols = line.split(",");
                    searchLine = cols[Integer.parseInt(args[0].trim()) - 1].replaceAll("^\"|\"$", "");
                    if (searchLine.regionMatches(true, 0, data, 0, data.length())) {
                        result.put(searchLine, line);
                        count++;
                    }
                }
                for (Map.Entry<String, String> pair : result.entrySet()) {
                    System.out.println(pair.getKey() + "[" + pair.getValue() + "]");
                }
                System.out.println("Строк:" + count);
                System.out.println("Время поиска:" + (double) (System.currentTimeMillis() - m) + " мс");
            } catch (ArrayIndexOutOfBoundsException | FileNotFoundException | NumberFormatException e) {
                if (e.getClass() == ArrayIndexOutOfBoundsException.class || e.getClass() == NumberFormatException.class) {
                    System.out.println("Не правильно задан параметр");
                } else {
                    System.out.println("Файл не найден");
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
