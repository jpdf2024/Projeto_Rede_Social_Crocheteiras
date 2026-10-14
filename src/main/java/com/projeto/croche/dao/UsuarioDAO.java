package com.projeto.croche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.croche.database_manager.DatabaseManager;

//Instância única da classe UsuarioDAO, método getInstance() retorna a instância única da classe, garantindo que apenas uma instância seja criada durante a execução do programa. Isso é útil para gerenciar o acesso aos dados do usuário de forma centralizada e consistente.
public class UsuarioDAO {
    private static class Holder {
        private static final UsuarioDAO INSTANCE = new UsuarioDAO();
    }

    private UsuarioDAO() {}

    public static UsuarioDAO getInstance() {
        return Holder.INSTANCE;
    }
    // Métodos para acessar e manipular os dados do usuário no banco de dados  
    public void salvarUsuario(String nome, String email, String senhaHash, String bio) {
        // Lógica para salvar o usuário no banco de dados
        try (Connection connection = DatabaseManager.conectar();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO usuarios (nome, email, senha, bio) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, senhaHash);
            statement.setString(4, bio);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Salvando usuário: " + nome + ", Email: " + email);
    }
}

