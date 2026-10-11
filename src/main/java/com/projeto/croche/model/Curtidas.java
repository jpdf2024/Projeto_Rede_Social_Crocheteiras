package com.projeto.croche.model;

public class Curtidas {
    // preciso do id da postagem e do id do usuario que curtiu
    private int id;
    private int idPostagem;
    private int idUsuario;
    private int quantidadeCurtidas;

    public int getQuantidadeCurtidas() {
        return quantidadeCurtidas;
    }

    public void setQuantidadeCurtidas(int quantidadeCurtidas) {
        if (quantidadeCurtidas < 0) {
            throw new IllegalArgumentException("Curtidas não podem ser negativas");
        }
        this.quantidadeCurtidas = quantidadeCurtidas;
    }

    // método para incrementar a quantidade de curtidas na postagem
    public void incrementarCurtidas() {
        this.quantidadeCurtidas++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("O id nao pode ser negativo");
        }

        this.id = id;
    }

    public int getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(int idPostagem) {
        if (idPostagem < 0) {
            throw new IllegalArgumentException("O id da postagem nao pode ser negativo");
        }

        this.idPostagem = idPostagem;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        if (idUsuario < 0) {
            throw new IllegalArgumentException("O id do usuario nao pode ser negativo");
        }

        this.idUsuario = idUsuario;
    }

    public Curtidas(int id, int idPostagem, int idUsuario) {
        setId(id);
        setIdPostagem(idPostagem);
        setIdUsuario(idUsuario);
        this.quantidadeCurtidas = 0;
    }
}
