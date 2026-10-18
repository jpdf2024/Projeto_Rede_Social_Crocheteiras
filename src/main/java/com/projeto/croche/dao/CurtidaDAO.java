package com.projeto.croche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.croche.database_manager.DatabaseManager;

public class CurtidaDAO {

    // Instância única da classe CurtidaDAO, método getInstance() retorna a
    // instância única da classe, garantindo que apenas uma instância seja criada
    // durante a execução do programa. Isso é útil para gerenciar o acesso aos dados
    // das curtidas de forma centralizada e consistente.
    private static class Holder {
        private static final CurtidaDAO INSTANCE = new CurtidaDAO();
    }

    private CurtidaDAO() {
    }

    public static CurtidaDAO getInstance() {
        return Holder.INSTANCE;
    }

        // Métodos para acessar e manipular os dados das postagens no banco de dados
    public void salvarCurtida(Long usuarioId, Long postagemId) {

        try (Connection connection = DatabaseManager.conectar();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO curtidas (usuario_id, postagem_id) VALUES (?, ?)")) {
            statement.setLong(1, usuarioId);
            statement.setLong(2, postagemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar curtida", e);
        }

        // Lógica para salvar a curtida no banco de dados
        System.out.println("Salvando curtida: Usuario ID: " + usuarioId + ", Postagem ID: " + postagemId);
    }


}




