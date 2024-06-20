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
