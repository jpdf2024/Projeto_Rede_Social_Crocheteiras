package com.projeto.croche.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.projeto.croche.database_manager.DatabaseManager;
import com.projeto.croche.model.Usuario;

public class AuthService {
    /* Autenticação de usuárias */
    public Optional<Usuario> autenticarUsuario(String email, String senha) {
        System.out.println("Autenticando usuária...");

        String sqlBusca = "SELECT u.id, u.nome, u.email, u.senha, u.bio " +
                     "FROM usuarios u " +
                     "WHERE u.email = ?";

         try (Connection conn = DatabaseManager.conectar(); 
         PreparedStatement pstmt = conn.prepareStatement(sqlBusca)) {
        
        pstmt.setString(1, email);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String senhaHash = rs.getString("senha");
                // Verifica se a senha fornecida corresponde ao hash armazenado no banco de dados
                if (BCrypt.checkpw(senha, senhaHash)) {
                    Usuario usuario = new Usuario(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            senhaHash,
                            rs.getString("bio"));

                    System.out.println("Usuária autenticada com sucesso!");
                    System.out.println("ID: " + usuario.getId());
                    return Optional.of(usuario);
                }
            }
            System.out.println("E-mail ou senha inválidos.");
            return Optional.empty();
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao autenticar usuária", e);
    }
    }

}
