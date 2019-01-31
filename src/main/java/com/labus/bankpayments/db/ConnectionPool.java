package com.labus.bankpayments.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static ConnectionPool poolInstance = null;
    private static volatile boolean isCreated = false;

    private BlockingQueue<Connection> connections;

    private int poolSize;
    private String url;
    private String login;
    private String password;

    public static ConnectionPool getInstance() {
        if (!isCreated) {
            synchronized (ConnectionPool.class) {
                poolInstance = new ConnectionPool();
                isCreated = true;
            }
        }
        return poolInstance;
    }

    private ConnectionPool() {
        this.poolSize = 10;
        this.url = "jdbc:mysql://localhost:3306/bankschema?serverTimezone=UTC";
        this.login = "root";
        this.password = "4844vtl";
        initPoolData();
    }

    private void initPoolData() {
        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
                Connection connection = DriverManager.getConnection(url, login, password);
                connections.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection takeConnection() throws SQLException {
        Connection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new SQLException();
        }
        return connection;
    }

    public void dispose() {
        Connection connection;
        while ((connection = connections.poll()) != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
    public void returnConnection(Connection connection){
        try {
            connections.put(connection);
        } catch (InterruptedException e) {

        }
    }

}
