package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String firstLine = input.readLine();
                    System.out.println(firstLine);

                    String[] parts = firstLine.split(" ");
                    if (parts.length > 1) {
                        String query = parts[1];
                        if (query.contains("/?msg=")) {
                            String value = query.substring(query.indexOf("/?msg=") + 6);

                            if ("Hello".equals(value)) {
                                output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                output.write("Hello, dear friend.".getBytes());
                            } else if ("Exit".equals(value)) {
                                output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                server.close();
                                break;
                            } else {
                                output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                output.write((value + "\r\n").getBytes());
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
}