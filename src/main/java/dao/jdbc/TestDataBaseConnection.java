package dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDataBaseConnection {
	public static void main(String[] args) {
        Connection connection = null;
        try {
            // Obtém a conexão usando a ConnectionFactory
            connection = ConnectionFactory.getConnection();
            
            // Verifica se a conexão não é nula
            if (connection != null) {
                System.out.println("Conexão bem sucedida!");
                
                // Executa uma consulta simples para verificar a conexão
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                
                // Exibe o resultado da consulta
                while (resultSet.next()) {
                    System.out.println("Resultado da consulta: " + resultSet.getInt(1));
                }
            } else {
                System.out.println("Falha ao obter conexão.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão ao finalizar
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
