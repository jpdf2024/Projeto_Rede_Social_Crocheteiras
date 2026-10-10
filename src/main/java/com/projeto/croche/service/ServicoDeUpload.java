package com.projeto.croche.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ServicoDeUpload {

    // Pasta onde os arquivos serão salvos no computador/servidor
    private static final String DIRETORIO_UPLOAD = "database/upload/";

    public String salvarArquivoNoDisco(byte[] bytesDoArquivo, String nomeOriginalDoArquivo) {
        try {
            // Garante que a pasta de uploads existe, senão ele cria
            Path caminhoDiretorio = Paths.get(DIRETORIO_UPLOAD);
            if (!Files.exists(caminhoDiretorio)) {
                Files.createDirectories(caminhoDiretorio);
            }

            // Define o caminho final onde o arquivo será guardado
            Path caminhoCompleto = caminhoDiretorio.resolve(System.currentTimeMillis() + "_" + nomeOriginalDoArquivo);

            // Salva os bytes fisicamente no disco rigido
            Files.write(caminhoCompleto, bytesDoArquivo);

            // Retorna a string do caminho relativo para salvar no objeto Postagem
            return caminhoCompleto.toString();
            
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o arquivo físico no computador: " + e.getMessage());
        }
    }
}
