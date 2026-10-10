package com.projeto.croche.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Postagem {
    private Long id;
    private Usuario autor;
    private String imagemUrl;
    private String legenda;
    private int quantidadeCurtidas;

    public void adicionarCurtida() {
        this.quantidadeCurtidas++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id < 0) {
            throw new IllegalArgumentException("O id nao pode ser negativo");
        }
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        if (autor == null || autor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do autor e obrigatorio");
        }

        this.autor = autor;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    // public void setImagemUrl(String imagemUrl) {
    // this.imagemUrl = imagemUrl;
    // }

    /*
     * public void setImagemUrl(String imagemUrl) {
     * if (imagemUrl == null || imagemUrl.isBlank()) {
     * this.imagemUrl = null;
     * return;
     * }
     * 
     * try {
     * URI uri = new URI(imagemUrl);
     * 
     * if (!"http".equalsIgnoreCase(uri.getScheme())
     * && !"https".equalsIgnoreCase(uri.getScheme())) {
     * throw new IllegalArgumentException("A URL deve usar http ou https");
     * }
     * 
     * if (uri.getHost() == null) {
     * throw new IllegalArgumentException("Informe uma URL válida");
     * }
     * 
     * this.imagemUrl = imagemUrl;
     * } catch (URISyntaxException e) {
     * throw new IllegalArgumentException("Informe uma URL válida", e);
     * }
     * }
     * 
     * public String getLegenda() {
     * return legenda;
     * }
     */

    public void setImagemUrl(String imagemUrl) {
        if (imagemUrl == null || imagemUrl.isBlank()) {
            throw new IllegalArgumentException("A imagem é obrigatória.");
        }

        String valorLimpo = imagemUrl.trim();

        // Tenta validar se é um link da web (http ou https)
        if (isUrlValida(valorLimpo)) {
            this.imagemUrl = valorLimpo;
            return;
        }

        // Se não for URL, tenta validar se é um caminho de arquivo físico válido no
        // computador
        if (isArquivoLocalValido(valorLimpo)) {
            this.imagemUrl = valorLimpo;
            return;
        }

        throw new IllegalArgumentException("Informe uma URL válida ou um caminho de arquivo de imagem existente.");
    }

    // Auxiliar para validar URLs da web
    private boolean isUrlValida(String texto) {
        try {
            URI uri = new URI(texto);
            String esquema = uri.getScheme();
            return ("http".equalsIgnoreCase(esquema) || "https".equalsIgnoreCase(esquema))
                    && uri.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    // Auxiliar para validar se o arquivo físico realmente existe na máquina
    private boolean isArquivoLocalValido(String caminho) {
        try {
            Path path = Paths.get(caminho);
            // Verifica se o arquivo existe no disco e não é um diretório
            return Files.exists(path) && !Files.isDirectory(path);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLegenda() {
        return legenda;
    }
    public void setLegenda(String legenda) {
        if (legenda != null && !legenda.isBlank()) {
            this.legenda = legenda;
        }
        // ou lançar exceção se for obrigatório
    }

    public int getQuantidadeCurtidas() {
        return quantidadeCurtidas;
    }

    public void setQuantidadeCurtidas(int quantidadeCurtidas) {
        if (quantidadeCurtidas < 0) {
            throw new IllegalArgumentException("Curtidas não podem ser negativas");
        }
        this.quantidadeCurtidas = quantidadeCurtidas;
    }

    public Postagem(Long id, Usuario autor, String imagemUrl, String legenda, int quantidadeCurtidas) {
        setId(id);
        setAutor(autor);
        setImagemUrl(imagemUrl);
        setLegenda(legenda);
        setQuantidadeCurtidas(quantidadeCurtidas);
    }

}
