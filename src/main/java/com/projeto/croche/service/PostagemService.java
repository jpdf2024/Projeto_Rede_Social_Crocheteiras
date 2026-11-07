package com.projeto.croche.service;

import com.projeto.croche.dao.PostagemDAO;
import com.projeto.croche.model.Postagem;

public class PostagemService {
    /*Gerencia a lógica de negócio para as postagens */
    public void criarPostagem(Postagem postagem) {
        PostagemDAO.getInstance().salvarPostagem(postagem);
    }
}
