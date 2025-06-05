package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader input = new BufferedReader(new FileReader(this.path))) {
            input.lines()
                    .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("#"))
                    .forEach(line -> {
                        if (!line.contains("=")) {
                            throw new IllegalArgumentException("Line doesn't contain '='");
                        }
                        String[] delimiter = line.split("=", 2);
                        if (delimiter[0].trim().isEmpty()) {
                            throw new IllegalArgumentException("Key is missing '='");
                        }
                        if (delimiter.length < 2 || delimiter[1].trim().isEmpty()) {
                            throw new IllegalArgumentException("Value is missing '='");
                        }
                        values.put(delimiter[0].trim(), delimiter[1].trim());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key is missing");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}