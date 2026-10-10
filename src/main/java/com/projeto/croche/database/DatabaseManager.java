package com.projeto.croche.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Caminho onde o arquivo do banco de dados será salvo
    private static final String URL_CONEXAO = "jdbc:sqlite:database/croche.db";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL_CONEXAO);
            System.out.println("Conexão com o SQLite estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    // Método para criar as tabelas iniciais automaticamente ao iniciar o app
    public static void criarTabelas() {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT UNIQUE NOT NULL, " +
                "senha TEXT NOT NULL, " +
                "bio TEXT" +
                ");";

        String sqlPostagens = "CREATE TABLE IF NOT EXISTS postagens (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario_id INTEGER NOT NULL, " +
                "imagem_url TEXT NOT NULL, " +
                "legenda TEXT, " +
                "FOREIGN KEY (usuario_id) REFERENCES usuarios(id)" +
                ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlPostagens);
            System.out.println("Tabelas verificadas/criadas com sucesso.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
