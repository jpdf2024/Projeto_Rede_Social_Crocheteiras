package com.projeto.croche;

import com.projeto.croche.database.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema Clube do Crochê...");
        
        // Garante que o banco e as tabelas existam
        DatabaseManager.criarTabelas();
    }
}
