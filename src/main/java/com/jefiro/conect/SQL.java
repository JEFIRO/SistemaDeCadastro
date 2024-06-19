package com.jefiro.conect;

import java.sql.*;
public class SQL {
    public SQL(String banco,String user, String password) {
        this.CAMINHO = "jdbc:sqlserver://localhost:1433;databaseName="+banco;
        this.USER = user;
        this.PASSWORD = password;
    }

    private  final String CAMINHO;
    private final String USER;
    private final String PASSWORD;
    private Connection connection;
    public void conectar(){
        try {
            String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            System.setProperty("jdbc.drivers", DRIVER);
            connection = DriverManager.getConnection(CAMINHO, USER, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insert(String query){
        try {
            connection.createStatement().executeUpdate(query);
            connection.createStatement().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet insertGetter(String sql){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar dados no banco de dados", e);
        }
    }
}
