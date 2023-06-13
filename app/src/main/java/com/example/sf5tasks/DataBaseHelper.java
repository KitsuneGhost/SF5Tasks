package com.example.sf5tasks;

import java.sql.Connection;

public class DataBaseHelper {
    private Connection connection;

    private static final String TAG = "DBHelper"; // тег для LogCat

    // парметры сервера и бд
    private final String host = "balarama.db.elephantsql.com";
    private final String database = "alnjrvin";
    private final int port = 5432;
    private final String user = "alnjrvin";
    private final String pass = "hIU8zyNQynyyyaBrZYy4sZ_l3IetE4EB";

    private String url = "jdbc:postgresql://%s:%d/%s"; // строка-шаблон, для создания ссылки

    public DataBaseHelper() {
        // конструктор
        // формирует полную ссылку для подключения к бд
        this.url = String.format(this.url, this.host, this.port, this.database);
    }

    public  void disconnect() {
        // метод для отключения от бд
        try {
            DisconnectTask disconnectTask = new DisconnectTask();
            disconnectTask.execute(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        // метод для подключения к бд
        ConnectTask connectTask = new ConnectTask();
        String[] data = new String[] {url, user, pass};
        connectTask.execute(data);
        try {
            connection = connectTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTasks(String[] data) {
        // метод для добавления данных в бд
        try {
            AddTask addTask = new AddTask(connection);
            addTask.execute(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}