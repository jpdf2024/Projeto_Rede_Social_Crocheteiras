package com.projeto.croche.model;

import com.projeto.croche.validador_email.EmailService;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id < 0) {
            throw new IllegalArgumentException("O id nao pode ser negativo");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome e obrigatorio");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       //inserir um Pattern regex para validar o email 10.10.2026
        // if (email == null || email.isBlank() || !email.contains("@")) {
        //     throw new IllegalArgumentException("Informe um email valido");
        // }
        EmailService emailService = new EmailService();
        if (!emailService.validarEmail(email)) {
            throw new IllegalArgumentException("Informe um email valido");
        }
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        if (senhaHash == null || senhaHash.isBlank()) {
            throw new IllegalArgumentException("O hash da senha e obrigatorio");
        }
        this.senhaHash = senhaHash;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Usuario(Long id, String nome, String email, String senhaHash, String bio) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setSenhaHash(senhaHash);
        setBio(bio);
    }
}
