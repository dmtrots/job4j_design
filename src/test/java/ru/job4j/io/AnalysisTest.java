package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void testUnavailablePeriods(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }

        File target = tempDir.resolve("target.txt").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> resultLines = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(resultLines::add);
        }

        List<String> expectedLines = List.of(
                "10:57:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        assertThat(resultLines).isEqualTo(expectedLines);
    }
}