package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private  final Map<FileProperty, List<Path>> fileMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {

        long size = attributes.size();
        String name = file.getFileName().toString();

        FileProperty fileProperty = new FileProperty(size, name);

        fileMap.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void printDublicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : fileMap.entrySet()) {
            List<Path> paths = entry.getValue();
            if (paths.size() > 1) {
                System.out.println("Duplicate files found for: " + entry.getKey().getName());
                paths.forEach(System.out::println);
            }
        }
    }
}