package com.projeto.croche;

import java.util.Scanner;

import com.projeto.croche.dao.UsuarioDAO;
import com.projeto.croche.database_manager.DatabaseManager;
import com.projeto.croche.model.Usuario;

public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando o sistema Clube do Crochê...");

        // Garante que o banco e as tabelas existam
        DatabaseManager.criarTabelas();

        Scanner scanner = new Scanner(System.in, java.nio.charset.StandardCharsets.UTF_8);
        int opcao = 0;

        do {
            System.out.println("\n=== CLUBE DO CROCHÊ (TESTE DE BACK-END) ===");
            System.out.println("1. Cadastrar Usuária");
            System.out.println("2. Fazer Nova Postagem");/*fazer login antes da postagem */
            System.out.println("3. Listar Postagens");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> System.out.println("-> Aqui você testaria o salvamento de postagem e upload.");
                case 3 -> System.out.println("-> Aqui você listaria os dados vindos do SQLite.");
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarUsuario(Scanner scanner) {

        System.out.print("Digite o nome da usuária: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email da usuária: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha da usuária: ");
        String senha = scanner.nextLine();

        System.out.print("Digite a bio da usuária: ");
        String bio = scanner.nextLine();

        Usuario usuario = new Usuario(null, nome, email, senha, bio);
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.salvarUsuario(usuario);

        // Aqui você chamaria o seu UsuarioDAO / Servico para cadastrar uma usuária
        System.out.println("-> Aqui você chamaria o seu UsuarioDAO / Servico.");
    }
}
