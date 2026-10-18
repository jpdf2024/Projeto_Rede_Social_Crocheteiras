package com.projeto.croche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.croche.database_manager.DatabaseManager;

public class PostagemDAO {

    // Instância única da classe PostagemDAO, método getInstance() retorna a
    // instância única da classe, garantindo que apenas uma instância seja criada
    // durante a execução do programa. Isso é útil para gerenciar o acesso aos dados
    // das postagens de forma centralizada e consistente.
    private static class Holder {
        private static final PostagemDAO INSTANCE = new PostagemDAO();
    }

    private PostagemDAO() {
    }

    public static PostagemDAO getInstance() {
        return Holder.INSTANCE;
    }

    // Métodos para acessar e manipular os dados das postagens no banco de dados
    public void salvarPostagem(Long usuarioId, String imagemURL, String legenda) {

        try (Connection connection = DatabaseManager.conectar();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO postagens (usuario_id, imagem_url, legenda) VALUES (?, ?, ?)")) {
            statement.setLong(1, usuarioId);
            statement.setString(2, imagemURL);
            statement.setString(3, legenda);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar postagem", e);
        }

        // Lógica para salvar a postagem no banco de dados
        System.out.println("Salvando postagem: " + legenda + ", Autor: " + usuarioId);
    }
}
