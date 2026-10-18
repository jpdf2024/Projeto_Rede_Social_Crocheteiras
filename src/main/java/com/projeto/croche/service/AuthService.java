package com.projeto.croche.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.projeto.croche.database_manager.DatabaseManager;

public class AuthService {
    /* Autenticação de usuárias */
    public void autenticarUsuario(String email, String senha) {
        System.out.println("Autenticando usuária...");

        boolean usuarioEncontrado = false;

        String sqlBusca = "SELECT u.id, u.senha " +
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
                    int usuarioId = rs.getInt("id");
                    usuarioEncontrado = true;

                    System.out.println("Usuária autenticada com sucesso!");
                    System.out.println("ID: " + usuarioId);
                }
            }
            if (!usuarioEncontrado) {
                System.out.println("E-mail ou senha inválidos.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar dados: " + e.getMessage());
        return; // Sai do método se der erro na busca
    }

    }
}
