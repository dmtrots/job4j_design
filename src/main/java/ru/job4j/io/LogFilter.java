package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        //return Collections.emptyList();
        List<String> list = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            input.lines().filter(line -> {
                    String[] delimiter = line.split(" ");
                    return delimiter[delimiter.length - 2].equals("404")
                            && !delimiter[delimiter.length - 1].equals("-");
                    }).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
