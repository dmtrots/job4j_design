package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        argsValidation(args);
        Path start = Paths.get(args[0]);
        String extension = args[1];
        List<Path> result = search(start, path -> path.toFile().getName().endsWith(extension));
        result.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void argsValidation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Количество передаваемых параметров не равно двум");
        }
        Path root = Paths.get(args[0]);
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", root.toAbsolutePath()));
        }
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", root.toAbsolutePath()));
        }
        String extension = args[1];
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("Имя расширения не начинается с точки");
        }
    }
}