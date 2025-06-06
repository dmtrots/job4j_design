package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            String line;
            String timeDown = null;
            boolean isDown = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] delimiter = line.split(" ");
                String status = delimiter[0];
                String time = delimiter[1];

                if (("400".equals(status) || "500".equals(status)) && !isDown) {
                    timeDown = time;
                    isDown = true;
                }
                if (("200".equals(status) || "300".equals(status)) && isDown) {
                    writer.println(timeDown + ";" + time + ';');
                    isDown = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
