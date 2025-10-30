package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");

        File source = new File(path);
        if (!source.exists() || !source.isFile()) {
            throw new IllegalArgumentException("Файл не найден: " + path);
        }

        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(source), Charset.forName("UTF-8"))) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Файл пустой: " + path);
            }

            String header = scanner.nextLine();
            String[] columns = header.split(delimiter);
            Map<String, Integer> headerIndex = new HashMap<>();

            for (int i = 0; i < columns.length; i++) {
                headerIndex.put(columns[i], i);
            }

            List<Integer> indexes = Arrays.stream(filters)
                    .map(headerIndex::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (indexes.isEmpty()) {
                throw new IllegalArgumentException("Не найдено ни одного столбца по фильтру");
            }

            lines.add(Arrays.stream(filters)
                    .collect(Collectors.joining(delimiter)));

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(delimiter);
                StringJoiner joiner = new StringJoiner(delimiter);
                for (int index : indexes) {
                    if (index < values.length) {
                        joiner.add(values[index]);
                    } else {
                        joiner.add("");
                    }
                }
                lines.add(joiner.toString());
            }
        }

        String result = lines.stream()
                .collect(Collectors.joining(System.lineSeparator()))
                .concat(System.lineSeparator());

        if ("stdout".equals(out)) {
            System.out.print(result);
        } else {
            try (PrintStream ps = new PrintStream(new FileOutputStream(out))) {
                ps.print(result);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не заданы параметры запуска");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }

    private static void validateArgs(ArgsName argsName) {
        for (String key : List.of("path", "delimiter", "out", "filter")) {
            if (argsName.get(key) == null || argsName.get(key).isEmpty()) {
                throw new IllegalArgumentException("Отсутствует параметр: " + key);
            }
        }
    }
}