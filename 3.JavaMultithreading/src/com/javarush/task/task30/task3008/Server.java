package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.MessageType.*;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Сервер запущен!");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка!");
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Сообщение не отправлено");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String userName = null;
            ConsoleHelper.writeMessage("Connection established with " + socket.getRemoteSocketAddress());

            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом " + socket.getRemoteSocketAddress() + " закрыто!");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            connection.send(new Message(NAME_REQUEST, "Введите имя:"));
            Message userName = connection.receive();

            while (true) {
                if (!userName.getType().equals(MessageType.USER_NAME) ||
                        userName.getData() == "" ||
                        connectionMap.containsKey(userName.getData())) {
                    connection.send(new Message(NAME_REQUEST, "Введите имя:"));
                    userName = connection.receive();
                } else break;
            }

            connectionMap.put(userName.getData(), connection);
            connection.send(new Message(NAME_ACCEPTED, "Вы добавлены в чат!"));
            ConsoleHelper.writeMessage(userName.getData() + " принято");

            return userName.getData();
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
                if (!userName.equals(connectionEntry.getKey())) {
                    connection.send(new Message(MessageType.USER_ADDED, connectionEntry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == TEXT) {
                    sendBroadcastMessage(new Message(TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("error");
                }
            }
        }
    }
}
