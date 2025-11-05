package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstLine = input.readLine();
                    System.out.println(firstLine);

                    String[] parts = firstLine.split(" ");
                    if (parts.length > 1) {
                        String query = parts[1];
                        if (query.contains("?")) {
                            String value = query.substring(query.indexOf("/?") + 2);
                            Map<String, String> params = parseParams(value);

                            String msg = params.get("msg");
                            if ("Hello".equals(msg)) {
                                output.write("Hello, dear friend.".getBytes());
                            } else if ("Exit".equals(msg)) {
                                server.close();
                                break;
                            } else {
                                output.write((msg + "\r\n").getBytes());
                            }
                        }
                    }

                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }

    private static Map<String, String> parseParams(String paramsPart) {
        Map<String, String> params = new HashMap<>();
        String[] pairs = paramsPart.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }
}