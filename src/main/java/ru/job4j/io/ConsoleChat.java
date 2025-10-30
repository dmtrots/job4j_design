package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        if (phrases.isEmpty()) {
            System.out.println("Файл с ответами бота пуст или не найден: " + botAnswers);
        }

        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        boolean botActive = true;
        String userInput = "";

        while (!OUT.equalsIgnoreCase(userInput)) {
            System.out.print("Вы: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            userInput = scanner.nextLine();
            log.add("Пользователь: " + userInput);

            if (STOP.equalsIgnoreCase(userInput)) {
                botActive = false;
                continue;
            }
            if (CONTINUE.equalsIgnoreCase(userInput)) {
                botActive = true;
                continue;
            }

            if (botActive && !phrases.isEmpty()) {
                String botReply = phrases.get(new Random().nextInt(phrases.size()));
                System.out.println("Бот: " + botReply);
                log.add("Бот: " + botReply);
            }
        }

        saveLog(log);
        System.out.println("Чат завершён");
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8")))) {
            for (String line : log) {
                writer.println(line);
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи лога в файл: " + path);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String logPath = "C:\\Projects\\job4j_design\\data\\chat_log.txt";
        String botAnswersPath = "C:\\Projects\\job4j_design\\data\\bot_answers.txt";

        ConsoleChat consoleChat = new ConsoleChat(logPath, botAnswersPath);
        consoleChat.run();
    }
}