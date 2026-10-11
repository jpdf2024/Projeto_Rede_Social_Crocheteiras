package com.projeto.croche.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Caminho onde o arquivo do banco de dados será salvo
    private static final String URL_CONEXAO = "jdbc:sqlite:database/croche.db";

    public static Connection conectar() {
        Connection conexao = null;

        // cria a pasta 'database' caso não exista
        try {
            // Garante que a pasta 'database' existe antes de conectar
            java.io.File pasta = new java.io.File("database");
            if (!pasta.exists()) {
                pasta.mkdirs();
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar pasta do banco de dados: " + e.getMessage());
        }

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

        String sqlCurtidas = "CREATE TABLE IF NOT EXISTS curtidas (" +
        // "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario_id INTEGER NOT NULL, " +
                "postagem_id INTEGER NOT NULL, " +
                "PRIMARY KEY (usuario_id, postagem_id), " +
                "FOREIGN KEY (usuario_id) REFERENCES usuarios(id), " +
                "FOREIGN KEY (postagem_id) REFERENCES postagens(id)" +
                ");";

        try (Connection conn = conectar();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sqlUsuarios);
            stmt.execute(sqlPostagens);
            stmt.execute(sqlCurtidas);
            System.out.println("Tabelas verificadas/criadas com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
