package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        int[][] matrix = Matrix.multiple(5);

        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            //output.write("Hello, world!".getBytes());
            for (int[] row : matrix) {
                for (int cell : row) {
                    String number = cell + "\t";
                    output.write(number.getBytes());
                }
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}