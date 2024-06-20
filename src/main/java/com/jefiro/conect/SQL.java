package com.jefiro.conect;

import java.sql.*;
public class SQL {
    private static final String CAMINHO = System.getenv("pathSQL");
    private static final String USER = System.getenv("userSQL");
    private static final String PASSWORD = System.getenv("passwordSQL");
    private Connection connection;

    public void conectar(){
        try {
            System.out.println(CAMINHO);
            System.out.println(USER);
            System.out.println(PASSWORD);
            String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            System.setProperty("jdbc.drivers", DRIVER);
            connection = DriverManager.getConnection(CAMINHO, USER, PASSWORD);
            System.out.println("conectado");
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void close(){
        try {
            connection.close();
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insert(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public ResultSet insertGetter(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
