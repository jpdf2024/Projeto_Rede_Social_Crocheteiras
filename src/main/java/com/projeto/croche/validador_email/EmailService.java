package com.projeto.croche.validador_email;

public class EmailService {

    private ValidadorEmail validador;

    public EmailService() {
        this(new RegexEmailAdapter());
    }

    public EmailService(ValidadorEmail validador) {
        this.validador = validador;
    }

    public boolean validarEmail(String email) {
        return validador.validar(email);
    }
}
