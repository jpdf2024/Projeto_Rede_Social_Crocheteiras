package com.projeto.croche.validador_email;

public class RegexEmailAdapter implements ValidadorEmail  {
    @Override 
    public boolean validar(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        if (email == null|| email.isBlank() || !email.contains("@")) return false;
        return email.matches(regex);
    }
}
